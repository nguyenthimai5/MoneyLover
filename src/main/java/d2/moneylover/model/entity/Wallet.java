package d2.moneylover.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "balance")
    private double balance;
    @Column(name = "image",columnDefinition = "TEXT")
    private String image;
    @Column(name = "create_date")
    private Date createDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_id",referencedColumnName = "id")
    private Currency currency;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "wallet")
    @JsonIgnore
    private List<Transaction> transactionList=new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "wallet_budget",joinColumns = @JoinColumn(name = "wallet_id"), inverseJoinColumns = @JoinColumn(name = "budget_id"))
    private List<Budget> budgetList = new ArrayList<>();



}
