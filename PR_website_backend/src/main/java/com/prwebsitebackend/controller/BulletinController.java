package com.prwebsitebackend.controller;

import com.prwebsitebackend.dto.BulletinDTO;
import com.prwebsitebackend.model.Bulletin;
import com.prwebsitebackend.payload.APIResponse;
import com.prwebsitebackend.service.BulletinService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bulletin")
public class BulletinController {
    private final BulletinService bulletin;
    ModelMapper mapper = new ModelMapper();

    public BulletinController(BulletinService bulletin) {
        this.bulletin = bulletin;
        TypeMap<Bulletin, BulletinDTO> typeMap = mapper.createTypeMap(Bulletin.class, BulletinDTO.class);
        typeMap.addMapping(Bulletin::getTitle, BulletinDTO::setName);
        typeMap.addMapping(Bulletin::getPost_header, BulletinDTO::setHeader);
        typeMap.addMapping(Bulletin::getPost_content, BulletinDTO::setContent);
    }

    @PostMapping("/add")
    private ResponseEntity<APIResponse> Create_Bulletin(@RequestBody BulletinDTO data) {
        Bulletin bullet = bulletin.create_bulletin(data);
        if(bullet!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("success", "Bulletin created successfully", mapper.map(bullet, BulletinDTO.class)));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse("error", "Bulletin creation failed", null));
    }
    @GetMapping("/all")
    private ResponseEntity<APIResponse> Get_Bulletin() {
        TypeToken<List<BulletinDTO>> token = new TypeToken<List<BulletinDTO>>() {};

        List<Bulletin>bulletinList= bulletin.get_bulletins();

        List<BulletinDTO>bulletins= mapper.map(bulletinList, token.getType());

        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("success", "Bulletin fetched successfully", bulletins));
    }

    @GetMapping("/{id}")
    private ResponseEntity<APIResponse> Get_Bulletin(@PathVariable String id) {
        Bulletin bulletinList= bulletin.get_bulletin(id);
        BulletinDTO bulletin= mapper.map(bulletinList, BulletinDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("success", "Bulletin fetched successfully", bulletin));
    }
}
