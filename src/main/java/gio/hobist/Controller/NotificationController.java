package gio.hobist.Controller;

import gio.hobist.Service.NotificationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class NotificationController {
    
    private final NotificationService notificationService;
    
    @GetMapping("/notifications")
    public String notificationsPage(Model model, HttpSession session) {
        var userId = (UUID) session.getAttribute("userId");
        var notifications = notificationService.getUserNotifications(Long.valueOf(userId.toString().hashCode()));
        model.addAttribute("notifications", notifications);
        return "notoficationsPage.html";
    }
}