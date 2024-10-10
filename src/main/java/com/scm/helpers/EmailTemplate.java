package com.scm.helpers;

public class EmailTemplate {

    public static String getVerificationEmailTemplate(String firstName, String verificationLink) {
        return "<html>" +
                "<head>" +
                "<style>" +
                "body {" +
                "    font-family: Arial, sans-serif;" +
                "    background-color: #f4f4f4;" +
                "    margin: 0;" +
                "    padding: 0;" +
                "}" +
                ".container {" +
                "    width: 100%;" +
                "    max-width: 600px;" +
                "    margin: 0 auto;" +
                "    background-color: #ffffff;" +
                "    padding: 20px;" +
                "    border-radius: 10px;" +
                "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);" +
                "}" +
                ".header {" +
                "    text-align: center;" +
                "    padding: 10px 0;" +
                "    border-bottom: 1px solid #eeeeee;" +
                "}" +
                ".header img {" +
                "    max-width: 100%;" +
                "    height: auto;" +
                "    border-radius: 10px 10px 0 0;" +
                "}" +
                ".content {" +
                "    padding: 20px;" +
                "}" +
                ".content p {" +
                "    font-size: 16px;" +
                "    color: #666666;" +
                "    line-height: 1.5;" +
                "}" +
                ".button {" +
                "    text-align: center;" +
                "    margin: 20px 0;" +
                "}" +
                ".button a {" +
                "    background-color: #007bff;" +
                "    color: #ffffff;" +
                "    padding: 10px 20px;" +
                "    text-decoration: none;" +
                "    border-radius: 5px;" +
                "    font-size: 16px;" +
                "}" +
                ".footer {" +
                "    text-align: center;" +
                "    padding: 10px 0;" +
                "    border-top: 1px solid #eeeeee;" +
                "    font-size: 14px;" +
                "    color: #aaaaaa;" +
                "}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class=\"container\">" +
                "    <div class=\"header\">" +
                "        <img src=\"https://your-logo-link.png\" alt=\"Logo\">" +
                "    </div>" +
                "    <div class=\"content\">" +
                "        <h2>Hello " + firstName + ",</h2>" +
                "        <p>Please click the button below to verify your email address:</p>" +
                "        <div class=\"button\">" +
                "            <a href=\"" + verificationLink + "\">Verify Email</a>" +
                "        </div>" +
                "        <p>If the button doesn't work, copy and paste this link into your browser:</p>" +
                "        <p>" + verificationLink + "</p>" +
                "        <p>Thank you,<br>The Smart Contact Manager Team</p>" +
                "    </div>" +
                "    <div class=\"footer\">" +
                "        <p>&copy; 2024 Smart Contact Manager. All rights reserved.</p>" +
                "    </div>" +
                "</div>" +
                "</body>" +
                "</html>";
    }
}
