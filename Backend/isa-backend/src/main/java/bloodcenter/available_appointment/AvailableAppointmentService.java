package bloodcenter.available_appointment;

import bloodcenter.available_appointment.dto.AvailableAppointmentsDTO;
import bloodcenter.branch_center.BranchCenter;
import bloodcenter.person.model.BCAdmin;
import bloodcenter.person.service.BCAdminService;
import bloodcenter.security.filter.AuthUtility;
import bloodcenter.utils.ObjectsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AvailableAppointmentService {
    private final AvailableAppointmentRepository repository;
    private final BCAdminService bcAdminService;
    @Autowired
    public AvailableAppointmentService(AvailableAppointmentRepository repository, BCAdminService
                                       bcAdminService){
        this.repository = repository;
        this.bcAdminService = bcAdminService;
    }

    public List<AvailableAppointmentsDTO> getAll(HttpServletRequest request) throws BCAdmin.BCAdminNotFoundException {
        String adminEmail = AuthUtility.getEmailFromRequest(request);
        BranchCenter branchCenter = bcAdminService.getBranchCenterByAdminEmail(adminEmail);
        List<AvailableAppointmentsDTO> appointmentsToReturn = new ArrayList<AvailableAppointmentsDTO>();
        List<AvailableAppointment> allAppointments = repository.getAvailableAppointmentByBranchCenterId(branchCenter.getId());
        for (AvailableAppointment availableAppointment : allAppointments) {
            appointmentsToReturn.add(ObjectsMapper.convertAvailableAppointmentToDTO(availableAppointment));
        }
        return appointmentsToReturn;
    }

    public void create(HttpServletRequest request,AvailableAppointmentsDTO appointmentsDTO) throws BCAdmin.BCAdminNotFoundException {
        String adminEmail = AuthUtility.getEmailFromRequest(request);
        BranchCenter branchCenter = bcAdminService.getBranchCenterByAdminEmail(adminEmail);
        AvailableAppointment appointment = ObjectsMapper.convertDTOToAvailableAppointment(appointmentsDTO);
        appointment.setBranchCenter(branchCenter);
        repository.save(appointment);
    }

    public List<AvailableAppointment> getByBranchCenterId(Long id) {
        return repository.getAvailableAppointmentByBranchCenterId(id);
    }
    
    public AvailableAppointment getByUserSelectedDateAndBcId(LocalDateTime dateTime, long branchCenterId) {
        for (AvailableAppointment availableAppointment:repository.findAll()) {
            if(availableAppointment.getBranchCenter().getId() == branchCenterId){
                if(availableAppointment.getStart().isBefore(dateTime) && availableAppointment.getEnd().isAfter(dateTime)){
                    return availableAppointment;
                }
            }
        }
        return null;
    }

    public AvailableAppointment getAvailableAppointmentById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void remove(AvailableAppointment availableAppointment)
    {
        repository.delete(availableAppointment);
    }
}
