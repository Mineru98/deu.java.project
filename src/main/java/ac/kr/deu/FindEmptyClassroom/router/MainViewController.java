package ac.kr.deu.FindEmptyClassroom.router;

import ac.kr.deu.FindEmptyClassroom.domain.Board.dto.DetailMapping;
import ac.kr.deu.FindEmptyClassroom.domain.Building.Building;
import ac.kr.deu.FindEmptyClassroom.domain.Course.Course;
import ac.kr.deu.FindEmptyClassroom.domain.LikeReply.dto.RankMapping;
import ac.kr.deu.FindEmptyClassroom.domain.Reply.Reply;
import ac.kr.deu.FindEmptyClassroom.domain.Req.RequestDTO;
import ac.kr.deu.FindEmptyClassroom.domain.User.User;
import ac.kr.deu.FindEmptyClassroom.service.board.BoardService;
import ac.kr.deu.FindEmptyClassroom.service.building.BuildingService;
import ac.kr.deu.FindEmptyClassroom.service.course.CourseService;
import ac.kr.deu.FindEmptyClassroom.service.reply.ReplyService;
import ac.kr.deu.FindEmptyClassroom.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "")
public class MainViewController {
    private final UserService userService;
    private final ReplyService replyService;
    private final BoardService boardService;
    private final CourseService courseService;
    private final BuildingService buildingService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainView(Model model, HttpServletRequest req) {
        model.addAttribute("title", "빈 강의실 좀 찾아줘");
        List<Building> buildingList = buildingService.findAllByUniversityId(1L);
        model.addAttribute("buildingArr", buildingList);
        // 세션 정보 로드
        HttpSession session = req.getSession();
        model.addAttribute("userId", session.getAttribute("userId"));
        model.addAttribute("name", session.getAttribute("name"));
        return "index.html";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchView(Model model, HttpServletRequest req) {
        RequestDTO dto = new RequestDTO();
        String universityId = req.getParameter("universityId");
        String[] courseDayOf = req.getParameterValues("courseDayOf");
        String[] courseTime = req.getParameterValues("courseTime");
        String roomName = req.getParameter("roomName");
        String buildingId = req.getParameter("buildingId");
        if (universityId != null && !universityId.isBlank()) {
            dto.setUniversityId(Long.valueOf(universityId));
        }
        if (buildingId != null && !buildingId.isBlank()) {
            dto.setBuildingId(Long.valueOf(buildingId));
        }
        if (courseDayOf != null && courseDayOf.length != 0) {
            List<Long> arr = new ArrayList<>();
            for (String value : courseDayOf) {
                arr.add(Long.valueOf(value));
            }
            dto.setCourseDayOf(arr);
        }
        if (courseTime != null && courseTime.length != 0) {
            List<Long> arr = new ArrayList<>();
            for (String value : courseTime) {
                arr.add(Long.valueOf(value));
            }
            dto.setCourseTime(arr);
        }
        if (roomName != null && !roomName.isBlank()) {
            dto.setRoomName(roomName);
        }
        model.addAttribute("title", "검색");
        List<Building> buildingList = buildingService.findAllByUniversityId(1L);
        model.addAttribute("buildingArr", buildingList);
        model.addAttribute("items", courseService.getAllByCondition(dto));
        // 세션 정보 로드
        HttpSession session = req.getSession();
        model.addAttribute("userId", session.getAttribute("userId"));
        model.addAttribute("name", session.getAttribute("name"));
        return "search.html";
    }

    @RequestMapping(value = "/rank", method = RequestMethod.GET)
    public String rankView(Model model, HttpServletRequest req) {
        // 세션 정보 로드
        HttpSession session = req.getSession();
        model.addAttribute("userId", session.getAttribute("userId"));
        model.addAttribute("name", session.getAttribute("name"));

        List<RankMapping> userList = userService.getRank();
        model.addAttribute("userList", userList);
        return "rank.html";
    }

    @RequestMapping(value = "/detail/{roomId}", method = RequestMethod.GET)
    public String detailView(
            @PathVariable Long roomId,
            Model model,
            HttpServletRequest req
    ) {
        model.addAttribute("roomId", roomId);
        // 세션 정보 로드
        HttpSession session = req.getSession();
        model.addAttribute("userId", session.getAttribute("userId"));
        model.addAttribute("name", session.getAttribute("name"));

        List<Course> courseList = courseService.getAllByRoomId(roomId);
        model.addAttribute("courseList", courseList);

        List<Reply> replyList = replyService.getAllByRoomId(roomId);
        model.addAttribute("replyList", replyList);

        DetailMapping board = boardService.getByRoomId(roomId, req);
        if (board != null) {
            model.addAttribute("boardId", board.getBoardId());
            model.addAttribute("viewCount", board.getViewCount());
            model.addAttribute("boardId", board.getBoardId());
            model.addAttribute("roomNumber", board.getRoomNumber());
            model.addAttribute(
                    "title",
                    board.getBuildingName() + "(" + board.getBuildingNumber() + "번 건물)" + " - " + board.getRoomNumber() + "호"
            );
            model.addAttribute(
                    "buildingName",
                    "건물 : " + board.getBuildingName() + "(" + board.getBuildingNumber() + "번)"
            );

            model.addAttribute(
                    "roomName",
                    "강의실 : " + board.getRoomNumber() + "호"
            );
            return "detail.html";
        } else {
            return "notfound.html";
        }
    }
}
