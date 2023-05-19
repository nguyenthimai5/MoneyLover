package d2.moneylover.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "currency")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "transfer")
    private double transfer;
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "last_modified_by")
    private String lastModifiedBy;
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;
    @OneToMany(mappedBy = "currency",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Wallet> walletList=new ArrayList<>();


}
