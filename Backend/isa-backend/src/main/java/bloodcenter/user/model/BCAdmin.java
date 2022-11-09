package bloodcenter.user.model;

import bloodcenter.branch_center.BranchCenter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "bc_admin")
@NoArgsConstructor
@Data
public class BCAdmin extends Person {

    @ManyToOne
    private BranchCenter branchCenter;

    public BCAdmin(String firstname, String lastname, String email) {
        super(firstname, lastname, email);
    }

    public static class BCAdminNotFoundException extends Exception {
        public BCAdminNotFoundException(String message){
            super(message);
        }
    }
}
