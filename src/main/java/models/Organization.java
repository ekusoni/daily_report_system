package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name=JpaConst.TABLE_ORG)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Organization {


    /*
     * id
     */
    @Id
    @Column(name=JpaConst.ORG_COL_ID)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    /**
     *組織名
     */
    @Column(name=JpaConst.ORG_COL_NAME,nullable=false)
    private String name;






}
