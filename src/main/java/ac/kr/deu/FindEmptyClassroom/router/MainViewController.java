package ac.kr.deu.FindEmptyClassroom.router;

import javax.servlet.http.HttpServletRequest;

import ac.kr.deu.FindEmptyClassroom.domain.Building.Building;
import ac.kr.deu.FindEmptyClassroom.domain.Req.RequestDTO;
import ac.kr.deu.FindEmptyClassroom.domain.Room.Room;
import ac.kr.deu.FindEmptyClassroom.service.building.BuildingService;
import ac.kr.deu.FindEmptyClassroom.service.course.CourseService;
import ac.kr.deu.FindEmptyClassroom.service.room.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "")
public class MainViewController {
  private final RoomService roomService;
  private final CourseService courseService;
  private final BuildingService buildingService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String mainView(Model model, HttpServletRequest req) {
    model.addAttribute("title", "메인 페이지");
    List<Building> buildingList = buildingService.findAllByUniversityId(1L);
    model.addAttribute("buildingArr", buildingList);
    return "index.html";
  }

  @RequestMapping(value = "/search", method = RequestMethod.GET)
  public String searchView(Model model, HttpServletRequest req) {
    RequestDTO dto = new RequestDTO();
    String universityId = req.getParameter("universityId");
    String courseDayOf = req.getParameter("courseDayOf");
    String courseTime = req.getParameter("courseTime");
    String roomName = req.getParameter("roomName");
    String buildingId = req.getParameter("buildingId");
    if (universityId != null && !universityId.isBlank()) {
      dto.setUniversityId(Long.valueOf(universityId));
    }
    if (buildingId != null && !buildingId.isBlank()) {
      dto.setBuildingId(Long.valueOf(buildingId));
    }
    if (courseDayOf != null && !courseDayOf.isBlank()) {
      dto.setCourseDayOf(Long.valueOf(courseDayOf));
    }
    if (courseTime != null && !courseTime.isBlank()) {
      dto.setCourseTime(Long.valueOf(courseTime));
    }
    if (roomName != null && !roomName.isBlank()) {
      dto.setRoomName(roomName);
    }
    log.info(dto.toString());
    model.addAttribute("title", "검색");
    List<Building> buildingList = buildingService.findAllByUniversityId(1L);
    model.addAttribute("buildingArr", buildingList);
    model.addAttribute("items", courseService.getAllByCondition(dto));
    return "search.html";
  }

  @RequestMapping(value = "/detail/{roomId}", method = RequestMethod.GET)
  public String detailView(@PathVariable Long roomId, Model model, HttpServletRequest req) {
    Room room = roomService.getOneByRoomId(roomId);
    if (room != null) {
      model.addAttribute("title", room.getBuilding().getBuildingName() + " - " + room.getRoomNumber());
      return "detail.html";
    } else {
      return "notfound.html";
    }
  }
}
