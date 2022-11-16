package bloodcenter.person.dto;

import bloodcenter.address.AddressDTO;
import bloodcenter.person.enums.Sex;
import bloodcenter.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    public Long id;
    public String firstname;
    public String lastname;
    public String email;
    public AddressDTO address;
    public String phone;
    public String nationalId;
    public Sex sex;
    public String occupation;
    public String information;
}
