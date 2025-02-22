package fpt.intern.fa_api.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendConfirmationEmail(String to, String formattedNumber) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Địa chỉ email người gửi
        helper.setFrom("tranbuuquyen2002@gmail.com");


        helper.setTo(to);

        // Tiêu đề email
        helper.setSubject("Xác Thực Tài Khoản");

        String htmlContent = "<html>\r\n"
                + "    <body>\r\n"
                + "    <div style='border-top: 4px solid transparent; border-image: linear-gradient(to right, blue, orangered, green) 1; border-image-slice: 1;'>\r\n"
                + "        <div style=\"display: flex;\">\r\n"
                + "            <img src='https://th.bing.com/th/id/R.2c9bd348ee7a312a9687317ae9c20c73?rik=FHe2MSJoJWl1pA&pid=ImgRaw&r=0' alt='Logo' width='70' height='70'>\r\n"
                + "            <h2>Xác Thực Tài Khoản</h2>\r\n"
                + "           \r\n"
                + "        </div>\r\n"
                + "        \r\n"
                + "        <p>Bạn đã thực hiện thao tác xác thực tài khoản trên hệ thống Quản lý Fresher Adcademy FPT software.</p>\r\n"
                + "         <p>Mã xác thực của bạn là: <strong>" + formattedNumber + "</strong></p>\r\n"
                + "    </div>\r\n"
                + "           \r\n"
                + "   \r\n"
                + "    </body>\r\n"
                + "</html>";

        // Đặt nội dung email dưới dạng HTML
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }

    public void sendAccountByByMail(String username, String email, String password) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Địa chỉ email người gửi
        helper.setFrom("tranbuuquyen2002@gmail.com");


        helper.setTo(email);

        // Tiêu đề email
        helper.setSubject("[FAMS] - Tài khoản được tạo thành công");

        String htmlContent = "<html>\r\n"
                + "    <body>\r\n"
                + "   	<p>	Hi <strong>" + username + "</strong></p>\r\n"
                + "		<p>Tài khoản đăng nhập vào hệ thống FAMS của bạn đã được tạo thành công.</p>\n"
                + "			<p>Vui lòng truy cập hệ thống theo thông tin sau:</p>"
                + "				<ul>"
                + "					<li>Username: " + email + " <strong> hoặc </strong> " + username + " </li>"
                + "					<li>Password: " + password + "</li>"
                + "				</ul>"
                + "			<p>Lưu ý: Vui lòng thay đổi mật khẩu sau khi đăng nhập.</p>"
                + "           \r\n"
                + "   \r\n"
                + "    </body>\r\n"
                + "</html>";

        // Đặt nội dung email dưới dạng HTML
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }
}
