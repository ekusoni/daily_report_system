package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Organization;

public class OrganizationConverter {


    public static Organization toModel(OrganizationView ov) {
         return new Organization(
                 ov.getId(),
                 ov.getDepartmentName());
    }

    public static OrganizationView toView(Organization o) {

        if(o==null) {
            return null;
        }

        return new OrganizationView(
                o.getId(),
                o.getDepartmentName());

    }


    /**
     *
     */
    public static List<OrganizationView> toViewList(List<Organization> list){
        List<OrganizationView> evs=new ArrayList<>();

        for(Organization o:list) {
            evs.add(toView(o));
        }

        return evs;


    }

    /**
     *
     */
    public static void copyViewToModel(Organization o,Organization ov) {
        o.setId(ov.getId());
        o.setDepartmentName(ov.getDepartmentName());
    }

}
