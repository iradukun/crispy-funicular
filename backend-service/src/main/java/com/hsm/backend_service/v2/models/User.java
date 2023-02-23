package com.hcm.backend_service.v2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hcm.backend_service.v2.audits.InitiatorAudit;
import com.hcm.backend_service.v2.enums.EGender;
import com.hcm.backend_service.v2.enums.ERole;
import com.hcm.backend_service.v2.enums.EUserStatus;
import com.hcm.backend_service.v2.fileHandling.File;
import lombok.*;
import org.hibernate.annotations.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotNull;
import java.util.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Proxy(lazy=false)
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name="users", uniqueConstraints = { @UniqueConstraint(columnNames={ "email" }), @UniqueConstraint(columnNames={ "mobile" })})
public class User extends InitiatorAudit implements UserDetails {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    @Column(columnDefinition = "VARCHAR(255)")
    private UUID id;

    @Email(message = "Email should be valid.")
    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    private EGender gender;

    @NotNull(message = "Please enter user's full name.")
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EUserStatus status = EUserStatus.PENDING;

    @JsonManagedReference
    @JoinColumn(name="profile_image_id")
    @OneToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private File profileImage;

    @Column(name="activation_code")
    private String activationCode;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Collection<Role> roles = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
    @JsonIgnore
    @NotNull(message = "Password is required.")
    @Column(name="password")
    private String password;

    @Transient
    private String confirmPassword;

//    OneToMany with appointment
    @JsonManagedReference
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "patient", orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Appointment> appointments = new ArrayList<>();

    public User(UUID user_id) {
    }

    public User(String email, String mobile, EGender gender, String fullName, EUserStatus status, File profileImage, String password, String confirmPassword) {
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.fullName = fullName;
        this.status = status;
        this.profileImage = profileImage;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public User(String email, String username, String fullName, String profilePicPath, Role role, String password, String confirmPassword) {
        super();
    }

    public User(String email, String fullName, String password, String confirmPassword, EGender gender, String mobile, Role role) {
        super();
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.gender = gender;
        this.mobile = mobile;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
