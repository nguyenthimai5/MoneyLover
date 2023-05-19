package d2.moneylover.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name",unique = true)
    private String name;
    @Column(name = "password",columnDefinition = "TEXT")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "status")
    private boolean status;
    @Column(name = "image",columnDefinition = "TEXT")
    private String image;
    @Column(name = "role",nullable = false)
    private String role;
    @Column(name = "provinder_type")
    private int provinderType;
    @Column(name = "provinder_id")
    private long provinderId;
    @Column(name = "accsess_token",columnDefinition = "TEXT")
    private String accsessToken;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Wallet> walletList=new ArrayList<>();
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Category> categoryList=new ArrayList<>();
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Config> configList=new ArrayList<>();
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Notification> notificationList=new ArrayList<>();

}
