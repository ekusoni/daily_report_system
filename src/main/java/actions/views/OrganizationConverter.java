package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Organization;

public class OrganizationConverter {


    public static Organization toModel(Organization ov) {
         return new Organization(
                 ov.getId(),
                 ov.getName());
    }

    public static OrganizationView toView(Organization o) {

        if(o==null) {
            return null;
        }

        return new OrganizationView(
                o.getId(),
                o.getName());

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
        o.setName(ov.getName());
    }

}
