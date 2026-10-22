package com.mingraud;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.TextAlignment;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import javafx.scene.paint.Color;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {

    private Stage stage;
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/mingraud";
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "riya8380";

private Connection getDatabaseConnection() throws SQLException {
    return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
}
private void testDatabaseConnection() {
    try (Connection connection = getDatabaseConnection()) {
        System.out.println("Mingraud database connected successfully!");
    } catch (SQLException e) {
        System.out.println("Database connection failed: " + e.getMessage());
    }
}

    // ================= COLORS =================

    private static final String NAVY = "#07333B";
    private static final String NAVY2 = "#0A4A53";
    private static final String TEAL = "#0B8C99";
    private static final String MINT = "#9BE8DC";
    private static final String BG = "#F3F8F8";
    private static final String WHITE = "#FFFFFF";
    private static final String TEXT = "#102B32";
    private static final String MUTED = "#6E858A";
    private static final String BORDER = "#D7E7E8";
    private static final String SOFT = "#E9F7F5";

    private String userName = "User";

    // animation references
    private Timeline breathingTimeline;
    private Timeline popTimeline;

    // ================= START =================

    @Override
    public void start(Stage primaryStage) {
        testDatabaseConnection();

        stage = primaryStage;

        stage.setTitle(
                "Mingraud | Smart Wellbeing Companion"
        );

        showWelcome();

        stage.show();
    }

    // ============================================================
    // WELCOME
    // ============================================================

    private void showWelcome() {

        BorderPane root = new BorderPane();

        

root.setStyle(
        "-fx-background-color: linear-gradient(to right, "
                + "#10152F" + ", " + "#29204F" + ", " + "#49316B" + ");"
);



        VBox left = new VBox(25);

        left.setAlignment(Pos.CENTER_LEFT);

        left.setPadding(
                new Insets(70, 60, 70, 90)
        );

        URL logoURL = getClass().getResource("/mingraud-logo.png");
Image logoImage = new Image(logoURL.toExternalForm());

ImageView logoView = new ImageView(logoImage);
logoView.setFitWidth(220);
logoView.setFitHeight(180);
logoView.setPreserveRatio(true);

        Label heading = text(
                "Your mind deserves\n" +
                        "a little more care.",
                52,
                WHITE,
                true
        );

        heading.setLineSpacing(5);

        Label description = text(
                "Understand your daily wellbeing indicators,\n" +
                        "recognize stress patterns, and take a\n" +
                        "meaningful pause when you need one.",
                18,
                "#D8E8E9",
                false
        );

        Button start = primaryButton(
                "GET STARTED   →"
        );

        start.setOnAction(e -> {
    System.out.println("GET STARTED CLICKED");
    showLogin();
});

        Label disclaimer = text(
                "Personal wellbeing companion  •  Not a medical diagnosis",
                12,
                "#B5D0D3",
                false
        );

        left.getChildren().addAll(
                heading,
                description,
                start,
                disclaimer
        );

        VBox right = new VBox();
        
right.setStyle(
        "-fx-background-color: linear-gradient(to bottom right, #FFF9FC, #F8E5F0);"
);

right.setMaxWidth(Double.MAX_VALUE);


        right.setAlignment(Pos.CENTER);
        
right.setStyle(
        "-fx-background-color: linear-gradient(to bottom right, #FFF9FC, #F8E5F0);"
);

right.setMaxWidth(Double.MAX_VALUE);


        
Image welcomeLogo = new Image(
        getClass().getResourceAsStream("/mingraud-logo.png")
);

ImageView welcomeLogoView = new ImageView(welcomeLogo);

welcomeLogoView.setFitWidth(380);
welcomeLogoView.setFitHeight(380);
welcomeLogoView.setPreserveRatio(true);

right.getChildren().add(welcomeLogoView);

        root.setLeft(left);
        root.setCenter(right);

        setScene(
                root,
                1360,
                850
        );
    }

    // ============================================================
    // LOGIN
    // ============================================================

    private void showLogin() {

        stopAnimations();

        BorderPane root = new BorderPane();

       
root.setStyle(
        "-fx-background-color: #FFF9FC;"
);


        // LEFT
        VBox left = new VBox(22);

        left.setPrefWidth(570);

        left.setAlignment(Pos.CENTER);

        left.setPadding(
                new Insets(50)
        );

        

left.setStyle(
        "-fx-background-color: #FFF4F8;"
);


java.net.URL logoURL =
        getClass().getResource("/mingraud-logo.png");

ImageView logoView = new ImageView();

if (logoURL != null) {
    Image logoImage = new Image(logoURL.toExternalForm());
    logoView.setImage(logoImage);
}

logoView.setFitWidth(220);
logoView.setFitHeight(180);
logoView.setPreserveRatio(true);

       


        Label heading = text(
                "A calmer mind\nstarts with a moment.",
                38,
                

                "#000000",

                true
        );

        heading.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER
        );

        Label sub = text(
                "Track your wellbeing.\n" +
                        "Understand your patterns.\n" +
                        "Take a calm break.",
                17,
                "#000000",
                false
        );

        sub.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER
        );

        left.getChildren().addAll(
                logoView,
                heading,
                sub
        );

        // RIGHT
        StackPane right = new StackPane();

        right.setStyle("-fx-background-color: #F4E8F8;");

        VBox card = new VBox(16);

        card.setMaxWidth(540);

        card.setPadding(
                new Insets(50)
        );

        card.setAlignment(Pos.CENTER);

        card.setStyle(
                "-fx-background-color:white;" +
                        "-fx-background-radius:28;" +
                        "-fx-border-color:" + BORDER + ";" +
                        "-fx-border-radius:28;"
        );

        Label title = text(
                "Welcome back",
                36,
                TEXT,
                true
        );

        Label subtitle = text(
                "Sign in to continue your wellbeing journey.",
                15,
                MUTED,
                false
        );

        TextField email = new TextField();

        email.setPromptText(
                "Enter your email"
        );

        styleField(email);

        PasswordField password =
                new PasswordField();

        password.setPromptText(
                "Enter your password"
        );

        styleField(password);

        Label emailLabel = text(
                "EMAIL",
                12,
                TEXT,
                true
        );

        Label passwordLabel = text(
                "PASSWORD",
                12,
                TEXT,
                true
        );

        Button signIn =
                primaryButton(
                        "SIGN IN   →"
                );

        signIn.setMaxWidth(
                Double.MAX_VALUE
        );

        
        // REAL CLICK ACTION
        signIn.setOnAction(e -> {

            String enteredEmail = email.getText().trim();
            String enteredPassword = password.getText();

            if (enteredEmail.isEmpty() || enteredPassword.isEmpty()) {
                showAlert("Please enter your email and password.");
                return;
            }

            String sql = "SELECT name FROM users WHERE email = ? AND password = ?";

            try (Connection connection = getDatabaseConnection();
                 java.sql.PreparedStatement statement =
                         connection.prepareStatement(sql)) {

                statement.setString(1, enteredEmail);
                statement.setString(2, enteredPassword);

                try (java.sql.ResultSet result = statement.executeQuery()) {

                    if (result.next()) {
                        userName = result.getString("name");
                        showDashboard();
                    } else {
                        showAlert("Invalid email or password.");
                    }
                }

            } catch (SQLException ex) {
                showAlert("Login failed: " + ex.getMessage());
            }
        });


        Label newUser = text(
                "New to Mingraud?",
                14,
                MUTED,
                false
        );

        Button create =
                linkButton(
                        "Create your account"
                );

        create.setOnAction(
                e -> showRegister()
        );

        HBox registerRow =
                new HBox(
                        8,
                        newUser,
                        create
                );

        registerRow.setAlignment(
                Pos.CENTER
        );

        Button back =
                linkButton(
                        "← Back to Welcome"
                );

        back.setOnAction(
                e -> showWelcome()
        );

        card.getChildren().addAll(
                title,
                subtitle,
                emailLabel,
                email,
                passwordLabel,
                password,
                signIn,
                registerRow,
                back
        );

        right.getChildren().add(card);

        root.setLeft(left);
        root.setCenter(right);

        setScene(
                root,
                1360,
                850
        );
    }

    // ============================================================
    // REGISTER
    // ============================================================

    private void showRegister() {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color:" + BG + ";"
        );

        VBox left = new VBox(25);

        left.setPrefWidth(570);

        left.setAlignment(Pos.CENTER);

        left.setPadding(
                new Insets(60)
        );

        left.setStyle(
        "-fx-background-color: linear-gradient(to bottom right, #F6DCE9, #EDE3F8);"
);
        java.net.URL logoURL =
        getClass().getResource("/mingraud-logo.png");

ImageView logoView = new ImageView();

if (logoURL != null) {
    Image logoImage = new Image(logoURL.toExternalForm());
    logoView.setImage(logoImage);
}

logoView.setFitWidth(180);
logoView.setFitHeight(150);
logoView.setPreserveRatio(true);
        Label heading = text(
                "Start your wellbeing\njourney today.",
                38,
                WHITE,
                true
        );

        heading.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER
        );

        Label description = text(
                "A simple space to check in,\n" +
                        "reflect and take meaningful breaks.",
                17,
                "#D5E7E8",
                false
        );

        description.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER
        );

        left.getChildren().addAll(
                logoView,
heading,
description
        );

        StackPane right = new StackPane();

        VBox card = new VBox(17);

        card.setMaxWidth(540);

        card.setPadding(
                new Insets(50)
        );

        card.setAlignment(Pos.CENTER);

        card.setStyle(
                "-fx-background-color:white;" +
                        "-fx-background-radius:28;" +
                        "-fx-border-color:" + BORDER + ";" +
                        "-fx-border-radius:28;"
        );

        Label title = text(
                "Create your Mingraud account",
                30,
                TEXT,
                true
        );

        TextField name =
                new TextField();

        name.setPromptText(
                "Your name"
        );

        styleField(name);

        TextField email =
                new TextField();

        email.setPromptText(
                "Email address"
        );

        styleField(email);

        PasswordField password =
                new PasswordField();

        password.setPromptText(
                "Create password"
        );

        styleField(password);

         Button create =
                primaryButton(
                        "CREATE ACCOUNT"
                );

        create.setMaxWidth(
                Double.MAX_VALUE
        );

        create.setOnAction(e -> {

            if (name.getText().trim().isEmpty()) {

                showAlert(
                        "Please enter your name."
                );

                return;
            }

            if (email.getText().trim().isEmpty()) {

                showAlert(
                        "Please enter your email."
                );

                return;
            }
            if (password.getText().trim().isEmpty()) {

    showAlert(
            "Please enter your password."
    );

    return;
}

            String enteredName = name.getText().trim();
String enteredEmail = email.getText().trim();
String enteredPassword = password.getText();

String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";

try (Connection connection = getDatabaseConnection();
     java.sql.PreparedStatement statement =
             connection.prepareStatement(sql)) {

    statement.setString(1, enteredName);
    statement.setString(2, enteredEmail);
    statement.setString(3, enteredPassword);

    statement.executeUpdate();
    System.out.println("Rows inserted: " + statement.getUpdateCount());

    userName = enteredName;

    showAlert("Account created successfully!");
    showDashboard();

} catch (SQLException ex) {
    showAlert("Registration failed: " + ex.getMessage());
}
});

        Button login =
                linkButton(
                        "← Back to Login"
                );

        login.setOnAction(
                e -> showLogin()
        );

        card.getChildren().addAll(
                title,
                name,
                email,
                password,
                create,
                login
        );

        right.getChildren().add(card);

        root.setLeft(left);
        root.setCenter(right);

        setScene(
                root,
                1360,
                850
        );
    }

    // ============================================================
    // DASHBOARD
    // ============================================================

   
private void showDashboard() {

    stopAnimations();

    BorderPane root = new BorderPane();
    root.setStyle(
        "-fx-background-color: linear-gradient(to bottom right, #FFF7FA, #F8E5F0, #F1E8FA);"
    );

    // LEFT SIDEBAR
    VBox sidebar = new VBox(16);
    sidebar.setPrefWidth(250);
    sidebar.setPadding(new Insets(32, 20, 28, 20));
    sidebar.setStyle(
        "-fx-background-color: linear-gradient(to bottom, #F6DCE9, #F1E5F8);"
    );

    Label logo = text("✦  MINGRAUD", 23, "#58354F", true);
    Label welcome = text("Welcome, " + userName + " 👋", 14, "#795D75", true);

    Region spacer = new Region();
    VBox.setVgrow(spacer, Priority.ALWAYS);

    Button dashboard = sideButton("⌂   Dashboard");
    Button assessment = sideButton("♡   Daily Check-in");
    Button calm = sideButton("☾   Calm Zone");
    Button weekly = sideButton("▦   Weekly Journey");
    Button logout = sideButton("←   Sign Out");

    // Pink-purple sidebar buttons
    for (Button button : java.util.Arrays.asList(
            dashboard, assessment, calm, weekly, logout)) {
        button.setTextFill(javafx.scene.paint.Color.web("#58354F"));
        button.setMaxWidth(Double.MAX_VALUE);
        button.setStyle(
            "-fx-background-color: rgba(255,255,255,0.68);" +
            "-fx-background-radius: 14;" +
            "-fx-padding: 13 14;" +
            "-fx-cursor: hand;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;"
        );
    }

    dashboard.setOnAction(e -> showDashboard());
    assessment.setOnAction(e -> showAssessment());
    calm.setOnAction(e -> showCalmZone());
    weekly.setOnAction(e -> showWeekly());
    logout.setOnAction(e -> showWelcome());

    Separator separator = new Separator();
    separator.setStyle("-fx-background-color: #E6C7D9;");

    sidebar.getChildren().addAll(
        logo,
        welcome,
        separator,
        dashboard,
        assessment,
        calm,
        weekly,
        spacer,
        logout
    );

    // MAIN CONTENT
    VBox content = new VBox(22);
    content.setPadding(new Insets(38, 42, 38, 42));
    content.setStyle("-fx-background-color: transparent;");

    Label heading = text(
        "Good to see you, " + userName + " ♡",
        32,
        "#58354F",
        true
    );

    Label sub = text(
        "Your personal wellbeing space — take a moment for yourself.",
        15,
        "#896D82",
        false
    );

    // ATTRACTIVE PASTEL CARDS
    HBox cards = new HBox(18);
    cards.setAlignment(Pos.CENTER);
    cards.setFillHeight(true);

    VBox checkInCard = dashboardCard(
        "♡  TODAY",
        "Daily Check-in",
        "Take a quick snapshot of how you feel today.",
        "CHECK IN  →",
        e -> showAssessment()
    );

    VBox calmCard = dashboardCard(
        "☾  CALM ZONE",
        "Take a Pause",
        "Explore calming activities and make space to breathe.",
        "EXPLORE  →",
        e -> showCalmZone()
    );

    VBox weeklyCard = dashboardCard(
        "✧  YOUR JOURNEY",
        "Weekly Journey",
        "Notice patterns and reflect on your wellbeing.",
        "VIEW JOURNEY  →",
        e -> showWeekly()
    );

    stylePastelCard(checkInCard, "#FBE2EC", "#A64D79");
    stylePastelCard(calmCard, "#EDE4FA", "#7957A5");
    stylePastelCard(weeklyCard, "#FCEBD9", "#A66B32");

    for (VBox card : java.util.Arrays.asList(
            checkInCard, calmCard, weeklyCard)) {
        card.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(card, Priority.ALWAYS);
    }

    cards.getChildren().addAll(
        checkInCard,
        calmCard,
        weeklyCard
    );

    // REMINDER PANEL
    VBox insight = new VBox(10);
    insight.setPadding(new Insets(24));
    insight.setMaxWidth(Double.MAX_VALUE);
    insight.setStyle(
        "-fx-background-color: rgba(255,255,255,0.88);" +
        "-fx-background-radius: 22;" +
        "-fx-border-color: #EBCDDD;" +
        "-fx-border-radius: 22;" +
        "-fx-effect: dropshadow(gaussian, rgba(130,75,105,0.10), 18, 0, 0, 5);"
    );

    Label insightTitle = text(
        "✧  A Gentle Reminder",
        21,
        "#8E456D",
        true
    );

    Label insightText = text(
        "You don't need to fix everything at once.\n" +
        "One small pause can be a meaningful start. ♡",
        15,
        "#795D75",
        false
    );

    insight.getChildren().addAll(
        insightTitle,
        insightText
    );

    content.getChildren().addAll(
        heading,
        sub,
        cards,
        insight
    );

    root.setLeft(sidebar);
    root.setCenter(content);

    setScene(root, 1360, 850);
}

// Styles the existing dashboard cards without changing their actions.
private void stylePastelCard(VBox card, String background, String accent) {

    card.setSpacing(14);
    card.setPadding(new Insets(23));
    card.setStyle(
        "-fx-background-color: " + background + ";" +
        "-fx-background-radius: 22;" +
        "-fx-border-color: rgba(255,255,255,0.95);" +
        "-fx-border-radius: 22;" +
        "-fx-border-width: 1.5;" +
        "-fx-effect: dropshadow(gaussian, rgba(100,60,90,0.13), 18, 0, 0, 5);"
    );

    for (javafx.scene.Node node : card.getChildren()) {

        if (node instanceof Label label) {
            label.setTextFill(
                javafx.scene.paint.Color.web(accent)
            );
            label.setWrapText(true);
        }

        if (node instanceof Button button) {
            button.setTextFill(
                javafx.scene.paint.Color.web("#FFFFFF")
            );
            button.setStyle(
                "-fx-background-color: " + accent + ";" +
                "-fx-background-radius: 12;" +
                "-fx-padding: 10 14;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
            );
        }
    }
}

    // ============================================================
    // DAILY WELLBEING ASSESSMENT
    // ============================================================

    private void showAssessment() {

        stopAnimations();

        VBox root = new VBox(18);
        
        root.setPadding(new Insets(32, 50, 32, 50));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color:" + BG + ";");

        Button back = linkButton("← Back to Dashboard");
        back.setOnAction(e -> showDashboard());

        Label title = text(
                "Daily Wellbeing Check-in",
                32, TEXT, true
        );

        Label subtitle = text(
                "Reflect on how you have been feeling today.",
                16, MUTED, false
        );

        Label instructions = text(
                "Choose a rating for each area. Your answers are for personal reflection.",
                14, MUTED, false
        );

        final int[] stressScore = {0};
final int[] sleepScore = {0};
final int[] energyScore = {0};
final int[] moodScore = {0};
final int[] focusScore = {0};
final int[] socialScore = {0};
final int[] relaxScore = {0};
        VBox questions = new VBox(7);
       questions.setPadding(new Insets(12));
        questions.setMaxWidth(850);
        questions.setStyle(
        "-fx-background-color: linear-gradient(to bottom right, #FFFFFF, #FFF4F8);" +
        "-fx-background-radius: 24;" +
        "-fx-border-color: #EAD6E7;" +
        "-fx-border-width: 1.2;" +
        "-fx-border-radius: 24;" +
        "-fx-effect: dropshadow(gaussian, rgba(100,60,100,0.10), 18, 0.15, 0, 5);"
);

      
VBox box1 = new VBox(10);
box1.setPadding(new Insets(12));
box1.setStyle(
    "-fx-background-color: #EAF8F5;" +
    "-fx-background-radius: 16;" +
    "-fx-border-color: #C9EAE3;" +
    "-fx-border-radius: 16;"
);

Label section1 = new Label("①  HOW YOU FEEL");
section1.setFont(Font.font("System", FontWeight.BOLD, 16));
section1.setStyle("-fx-text-fill: #000000;");
box1.getChildren().addAll(
    section1,
    ratingQuestion(
        "1. How stressed do you feel?",
        new String[]{"😌", "🙂", "😐", "😟", "😣", "😫"},
        stressScore
    ),
    ratingQuestion(
        "2. Difficulty sleeping?",
        new String[]{"0", "1", "2", "3", "4", "5"},
        sleepScore
    ),
    ratingQuestion(
        "3. How low was your energy?",
        new String[]{"Not at all", "Very little", "A little",
            "Moderately", "Quite a bit", "Very much"},
        energyScore
    )
);

VBox box2 = new VBox(10);
box2.setPadding(new Insets(12));
box2.setStyle(
    "-fx-background-color: #F0F0FF;" +
    "-fx-background-radius: 16;" +
    "-fx-border-color: #D9D7FF;" +
    "-fx-border-radius: 16;"
);

Label section2 = new Label("②  YOUR ROUTINE");
section2.setFont(Font.font("System", FontWeight.BOLD, 16));
section2.setStyle("-fx-text-fill: #000000;");
box2.getChildren().addAll(
    section2,
    ratingQuestion(
        "4. How low was your mood?",
        new String[]{"Not at all", "Very little", "A little",
            "Moderately", "Quite a bit", "Very much"},
        moodScore
    ),
    ratingQuestion(
        "5. Difficulty focusing?",
        new String[]{"Not at all", "Very little", "A little",
            "Moderately", "Quite a bit", "Very much"},
        focusScore
    ),
    ratingQuestion(
        "6. Connection with others?",
        new String[]{"😔", "🙁", "😐", "🙂", "😊", "🤗"},
        socialScore
    )
);

VBox box3 = new VBox(10);
box3.setPadding(new Insets(12));
box3.setStyle(
    "-fx-background-color: #FFF6E9;" +
    "-fx-background-radius: 16;" +
    "-fx-border-color: #F5DFBC;" +
    "-fx-border-radius: 16;"
);

Label section3 = new Label("③  SELF-REFLECTION");
section3.setFont(Font.font("System", FontWeight.BOLD, 16));
section3.setStyle("-fx-text-fill: #000000;");
box3.getChildren().addAll(
    section3,
    ratingQuestion(
        "7. How difficult was it to relax?",
        new String[]{"Not at all", "Very little", "A little",
            "Moderately", "Quite a bit", "Very much"},
        relaxScore
    )
);

questions.getChildren().setAll(box1, box2, box3);
box1.setMaxWidth(Double.MAX_VALUE);
box2.setMaxWidth(Double.MAX_VALUE);
box3.setMaxWidth(Double.MAX_VALUE);

box1.setFillWidth(true);
box2.setFillWidth(true);
box3.setFillWidth(true);

questions.setFillWidth(true);
questions.setSpacing(14);

        
    Label note = text(
        "For personal reflection, not a medical diagnosis.",
        12, MUTED, false
    );

    Button submit = primaryButton("VIEW MY SNAPSHOT  →");
    submit.setMaxWidth(Double.MAX_VALUE);
    submit.setOnAction(e -> {
        double score =
            stressScore[0] + sleepScore[0] + energyScore[0] +
            moodScore[0] + focusScore[0] + socialScore[0] +
            relaxScore[0];

        showResult(score);
    });

    // Questions ke liye alag scroll area
    ScrollPane questionScroll = new ScrollPane(questions);
    questionScroll.setFitToWidth(true);
    questionScroll.setFitToHeight(false);
    questionScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    questionScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    questionScroll.setPannable(true);
    questionScroll.setStyle(
        "-fx-background: transparent;" +
        "-fx-background-color: transparent;"
    );

    // Right-side submit panel
    VBox actionPanel = new VBox(18);
    actionPanel.setAlignment(Pos.CENTER);
    actionPanel.setPadding(new Insets(24, 16, 24, 16));
    actionPanel.setPrefWidth(230);
    actionPanel.setMinWidth(200);
    actionPanel.setMaxHeight(Double.MAX_VALUE);
    actionPanel.setStyle(
        "-fx-background-color: white;" +
        "-fx-background-radius: 20;" +
        "-fx-border-color: #EAD6E7;" +
        "-fx-border-radius: 20;" +
        "-fx-padding: 18;"
    );

    Label panelTitle = text("YOUR DAILY", 18, TEXT, true);
    Label panelSubtitle = text("WELLBEING SNAPSHOT", 16, TEXT, true);
    Label panelDescription = text(
        "Finish your ratings, then view your personal snapshot.",
        13, MUTED, false
    );
    panelDescription.setWrapText(true);

    actionPanel.getChildren().addAll(
        panelTitle,
        panelSubtitle,
        panelDescription,
        new javafx.scene.layout.Region(),
        note,
        submit
    );

    // Left questions + right action panel
    HBox contentRow = new HBox(18);
    contentRow.setAlignment(Pos.TOP_CENTER);
    contentRow.getChildren().addAll(questionScroll, actionPanel);
    HBox.setHgrow(questionScroll, Priority.ALWAYS);
    VBox.setVgrow(contentRow, Priority.ALWAYS);

    // Root layout
    root.setMinHeight(0);
    root.setSpacing(10);
    root.setPadding(new Insets(18, 28, 18, 28));

    root.getChildren().setAll(
        back, title, subtitle, instructions, contentRow
    );

    ScrollPane outerScroll = new ScrollPane(root);
    outerScroll.setFitToWidth(true);
    outerScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    outerScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

    setScene(outerScroll, 1360, 850);
}

    // ============================================================
    // RESULT
    // ============================================================

    private void showResult(
            double score
    ) {

        String level;

        String message;

        if (score < 12) {

            level = "LOW";

            message =
                    "Your current wellbeing indicators look relatively balanced.";

        } else if (score < 21) {

            level = "MODERATE";

            message =
                    "Some wellbeing indicators suggest that a short pause may be useful.";

        } else {

            level = "HIGH";

            message =
                    "Several indicators suggest that taking some time to rest and reconnect may be helpful.";
        }

        VBox root =
                new VBox(24);

        root.setAlignment(
                Pos.CENTER
        );

        root.setPadding(
                new Insets(50)
        );

        root.setStyle(
                "-fx-background-color:" + BG + ";"
        );

        Label title =
                text(
                        "Your Wellbeing Snapshot",
                        36,
                        TEXT,
                        true
                );

        Label levelLabel =
                text(
                        level,
                        50,
                        TEAL,
                        true
                );

        Label messageLabel =
                text(
                        message,
                        17,
                        MUTED,
                        false
                );

        messageLabel.setWrapText(true);

        messageLabel.setMaxWidth(650);

        messageLabel.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER
        );

        Button calm =
                primaryButton(
                        "TAKE A CALM BREAK   →"
                );

        calm.setOnAction(
                e -> showCalmZone()
        );

        Button dashboard =
                linkButton(
                        "← Back to Dashboard"
                );

        dashboard.setOnAction(
                e -> showDashboard()
        );

        root.getChildren().addAll(
                title,
                levelLabel,
                messageLabel,
                calm,
                dashboard
        );

        setScene(
                root,
                1360,
                850
        );
    }

    // ============================================================
   
    // ============================================================
    // CALM ZONE - ALL 6 GAMES
    // ============================================================

    private void showCalmZone() {

        stopAnimations();

        VBox root = new VBox(20);
        root.setPadding(new Insets(32));
        root.setFillWidth(true);
        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        // Back button
        Button back = linkButton("← Back to Dashboard");
        back.setOnAction(e -> showDashboard());

        // Heading
        Label title = text(
                "Calm Zone",
                34,
                TEXT,
                true
        );

        Label subtitle = text(
                "Choose an activity for a short mindful break.",
                15,
                MUTED,
                false
        );

       
      
        // ROW 1: First three games
        HBox row1 = new HBox(16);
        row1.setAlignment(Pos.CENTER_LEFT);
        row1.setFillHeight(true);
        HBox.setHgrow(row1, Priority.ALWAYS);

        VBox breathingCard = activityCard(
                "◉",
                "Breathing Bubble",
                "Breathe in, hold, and slowly breathe out.",
                e -> showBreathing()
        );

        VBox memoryCard = activityCard(
                "◇",
                "Memory Match",
                "Match all pairs and exercise your attention.",
                e -> showMemoryMatch()
        );

        VBox popCard = activityCard(
                "✦",
                "Pop the Stress",
                "Pop bubbles and enjoy a quick mindful break.",
                e -> showPopStress()
        );

        breathingCard.setMaxWidth(Double.MAX_VALUE);
        memoryCard.setMaxWidth(Double.MAX_VALUE);
        popCard.setMaxWidth(Double.MAX_VALUE);

        HBox.setHgrow(breathingCard, Priority.ALWAYS);
        HBox.setHgrow(memoryCard, Priority.ALWAYS);
        HBox.setHgrow(popCard, Priority.ALWAYS);

        row1.getChildren().addAll(
                breathingCard,
                memoryCard,
                popCard
        );


        // Row 2: Remaining three games
        HBox row2 = new HBox(16);
        row2.setAlignment(Pos.CENTER_LEFT);
        row2.setFillHeight(true);
        HBox.setHgrow(row2, Priority.ALWAYS);

        VBox puzzleCard = activityCard(
                "🧩",
                "Quick Puzzle",
                "Solve the mini number puzzle.",
                e -> showQuickPuzzle()
        );

        VBox colorCard = activityCard(
                "🎨",
                "Color Calm",
                "Choose a calming color and relax.",
                e -> showColorCalm()
        );

        VBox focusCard = activityCard(
                "🎯",
                "Focus Challenge",
                "Test your attention and reaction.",
                e -> showFocus()
        );

        puzzleCard.setMaxWidth(Double.MAX_VALUE);
        colorCard.setMaxWidth(Double.MAX_VALUE);
        focusCard.setMaxWidth(Double.MAX_VALUE);

        HBox.setHgrow(puzzleCard, Priority.ALWAYS);
        HBox.setHgrow(colorCard, Priority.ALWAYS);
        HBox.setHgrow(focusCard, Priority.ALWAYS);

        row2.getChildren().addAll(
                puzzleCard,
                colorCard,
                focusCard
        );

        // Add everything to the screen
        root.getChildren().addAll(
                back,
                title,
                subtitle,
                row1,
                row2
        );

        setScene(root, 1360, 850);
    }

    // ============================================================
    // GAME 1 - BREATHING BUBBLE
    // ============================================================

    private void showBreathing() {

        stopAnimations();

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color:" + BG + ";"
        );

        VBox center =
                new VBox(20);

        center.setAlignment(
                Pos.CENTER
        );

        center.setPadding(
                new Insets(30)
        );

        Button back =
                linkButton(
                        "← Back to Calm Zone"
                );

        back.setOnAction(
                e -> {
                    stopAnimations();
                    showCalmZone();
                }
        );

        Label title =
                text(
                        "Breathing Bubble",
                        34,
                        TEXT,
                        true
                );

        Label instruction =
                text(
                        "Press START when you're ready.",
                        20,
                        TEAL,
                        true
                );

        StackPane bubblePane =
                new StackPane();

        bubblePane.setPrefSize(
                380,
                380
        );

        Circle glow =
                new Circle(
                        125,
                        Color.web(
                                "#DDF8F3"
                        )
                );

        Circle bubble =
                new Circle(
                        90,
                        Color.web(MINT)
                );

        bubble.setStroke(
                Color.web(TEAL)
        );

        bubble.setStrokeWidth(3);

        Label bubbleText =
                text(
                        "READY",
                        19,
                        NAVY,
                        true
                );

        bubblePane.getChildren().addAll(
                glow,
                bubble,
                bubbleText
        );

        Button start =
                primaryButton(
                        "START BREATHING"
                );

        start.setOnAction(e -> {

            if (breathingTimeline != null) {

                breathingTimeline.stop();
            }

            // 12 second cycle:
            // 4 inhale
            // 2 hold
            // 6 exhale

            breathingTimeline =
                    new Timeline(

                            new KeyFrame(
                                    Duration.ZERO,
                                    ev -> {

                                        instruction.setText(
                                                "Breathe in slowly..."
                                        );

                                        bubbleText.setText(
                                                "INHALE"
                                        );
                                    }
                            ),

                            new KeyFrame(
                                    Duration.seconds(4),
                                    ev -> {

                                        instruction.setText(
                                                "Hold gently..."
                                        );

                                        bubbleText.setText(
                                                "HOLD"
                                        );
                                    }
                            ),

                            new KeyFrame(
                                    Duration.seconds(6),
                                    ev -> {

                                        instruction.setText(
                                                "Let the breath out slowly..."
                                        );

                                        bubbleText.setText(
                                                "EXHALE"
                                        );
                                    }
                            ),

                            new KeyFrame(
                                    Duration.seconds(12),
                                    ev -> {

                                        instruction.setText(
                                                "Nice. Take another calm breath."
                                        );

                                        bubbleText.setText(
                                                "DONE"
                                        );
                                    }
                            )
                    );

            ScaleTransition inhale =
                    new ScaleTransition(
                            Duration.seconds(4),
                            bubble
                    );

            inhale.setFromX(0.75);
            inhale.setFromY(0.75);
            inhale.setToX(1.55);
            inhale.setToY(1.55);

            ScaleTransition hold =
                    new ScaleTransition(
                            Duration.seconds(2),
                            bubble
                    );

            hold.setFromX(1.55);
            hold.setFromY(1.55);
            hold.setToX(1.55);
            hold.setToY(1.55);

            ScaleTransition exhale =
                    new ScaleTransition(
                            Duration.seconds(6),
                            bubble
                    );

            exhale.setFromX(1.55);
            exhale.setFromY(1.55);
            exhale.setToX(0.75);
            exhale.setToY(0.75);

            SequentialTransition breathing =
                    new SequentialTransition(
                            inhale,
                            hold,
                            exhale
                    );

            breathingTimeline.setCycleCount(
                    Animation.INDEFINITE
            );

            breathingTimeline.play();

            breathing.setCycleCount(
                    Animation.INDEFINITE
            );

            breathing.play();

            start.setText(
                    "BREATHING..."
            );
        });

        center.getChildren().addAll(
                topBar(back),
                title,
                instruction,
                bubblePane,
                start
        );

        root.setCenter(center);

        setScene(
                root,
                1360,
                850
        );
    }

    // ============================================================
    // GAME 2 - MEMORY MATCH
    // ============================================================

    private void showMemoryMatch() {

        stopAnimations();

        VBox root =
                new VBox(18);

        root.setAlignment(
                Pos.CENTER
        );

        root.setPadding(
                new Insets(25)
        );

        root.setStyle(
                "-fx-background-color:" + BG + ";"
        );

        Button back =
                linkButton(
                        "← Back to Calm Zone"
                );

        back.setOnAction(
                e -> showCalmZone()
        );

        Label title =
                text(
                        "Memory Match",
                        32,
                        TEXT,
                        true
                );

        Label status =
                text(
                        "Find all matching pairs.",
                        17,
                        MUTED,
                        false
                );

        GridPane grid =
                new GridPane();

        grid.setHgap(12);
        grid.setVgap(12);

        grid.setAlignment(
                Pos.CENTER
        );

        List<String> values =
                new ArrayList<>(
                        Arrays.asList(
                                "🌿", "🌿",
                                "☀", "☀",
                                "☁", "☁",
                                "💧", "💧",
                                "🌙", "🌙",
                                "⭐", "⭐"
                        )
                );

        Collections.shuffle(values);

        Button[] first = new Button[1];

        int[] matched = {0};

        for (int i = 0; i < values.size(); i++) {

            Button card =
                    new Button("?");

            card.setPrefSize(
                    90,
                    75
            );

            card.setFont(
                    Font.font(
                            "System",
                            FontWeight.BOLD,
                            26
                    )
            );

            card.setStyle(
                    "-fx-background-color:" +
                            NAVY +
                            ";" +
                            "-fx-text-fill:white;" +
                            "-fx-background-radius:16;"
            );

            final int index = i;

            card.setOnAction(e -> {

                if (card.getText().equals("?")
                        && matched[0] < values.size()) {

                    card.setText(
                            values.get(index)
                    );

                    if (first[0] == null) {

                        first[0] = card;

                    } else {

                        Button previous =
                                first[0];

                        String a =
                                previous.getText();

                        String b =
                                card.getText();

                        if (a.equals(b)) {

                            previous.setDisable(
                                    true
                            );

                            card.setDisable(
                                    true
                            );

                            matched[0] += 2;

                            status.setText(
                                    "Match! ✓"
                            );

                            if (matched[0]
                                    == values.size()) {

                                status.setText(
                                        "Amazing! All pairs matched 🎉"
                                );
                            }

                        } else {

                            PauseTransition pause =
                                    new PauseTransition(
                                            Duration.seconds(0.65)
                                    );

                            pause.setOnFinished(
                                    ev -> {

                                        previous.setText(
                                                "?"
                                        );

                                        card.setText(
                                                "?"
                                        );

                                        status.setText(
                                                "Try another pair."
                                        );
                                    }
                            );

                            pause.play();
                        }

                        first[0] = null;
                    }
                }
            });

            grid.add(
                    card,
                    i % 4,
                    i / 4
            );
        }

        root.getChildren().addAll(
                topBar(back),
                title,
                status,
                grid
        );

        setScene(
                root,
                1360,
                850
        );
    }

    // ============================================================
    // GAME 3 - POP THE STRESS
    // ============================================================

    private void showPopStress() {

        stopAnimations();

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color:" + BG + ";"
        );

        VBox top =
                new VBox(8);

        top.setAlignment(
                Pos.CENTER
        );

        top.setPadding(
                new Insets(20)
        );

        Label title =
                text(
                        "Pop the Stress",
                        32,
                        TEXT,
                        true
                );

        Label scoreLabel =
                text(
                        "Score: 0",
                        19,
                        TEAL,
                        true
                );

        Label instruction =
                text(
                        "Pop the bubbles before the timer ends!",
                        14,
                        MUTED,
                        false
                );

        top.getChildren().addAll(
                title,
                scoreLabel,
                instruction
        );

        Pane area =
                new Pane();

        area.setPrefSize(
                900,
                600
        );

        area.setStyle(
                "-fx-background-color:#E9F7F5;" +
                        "-fx-background-radius:25;"
        );

        Button start =
                primaryButton(
                        "START 20 SECOND GAME"
                );

        start.setLayoutX(20);
        start.setLayoutY(20);

        area.getChildren().add(start);

        final int[] score = {0};

        final int[] seconds = {20};

        start.setOnAction(e -> {

            score[0] = 0;

            seconds[0] = 20;

            scoreLabel.setText(
                    "Score: 0"
            );

            area.getChildren().clear();

            Label timer =
                    text(
                            "20s",
                            17,
                            TEAL,
                            true
                    );

            timer.setLayoutX(20);
            timer.setLayoutY(20);

            area.getChildren().add(
                    timer
            );

            popTimeline =
                    new Timeline(
                            new KeyFrame(
                                    Duration.seconds(1),
                                    ev -> {

                                        seconds[0]--;

                                        timer.setText(
                                                seconds[0] + "s"
                                        );

                                        if (seconds[0] <= 0) {

                                            popTimeline.stop();

                                            area.getChildren().clear();

                                            Label done =
                                                    text(
                                                            "Well done! 🎉\n" +
                                                                    "You popped " +
                                                                    score[0] +
                                                                    " bubbles.",
                                                            24,
                                                            TEAL,
                                                            true
                                                    );

                                            done.setLayoutX(300);
                                            done.setLayoutY(250);

                                            area.getChildren().add(
                                                    done
                                            );
                                        }

                                        if (seconds[0] > 0) {

                                            addStressBubble(
                                                    area,
                                                    score,
                                                    scoreLabel
                                            );
                                        }
                                    }
                            )
                    );

            popTimeline.setCycleCount(
                    Animation.INDEFINITE
            );

            popTimeline.play();

            addStressBubble(
                    area,
                    score,
                    scoreLabel
            );
        });

        Button back =
                linkButton(
                        "← Back to Calm Zone"
                );

        back.setOnAction(e -> {

            stopAnimations();

            showCalmZone();
        });

        VBox bottom =
                new VBox(
                        10,
                        back
                );

        bottom.setAlignment(
                Pos.CENTER
        );

        root.setTop(top);
        root.setCenter(area);
        root.setBottom(bottom);

        setScene(
                root,
                1360,
                850
        );
    }

    private void addStressBubble(
            Pane area,
            int[] score,
            Label scoreLabel
    ) {

        Random random =
                new Random();

        double size =
                45 + random.nextInt(35);

        Circle bubble =
                new Circle(
                        size / 2
                );

        bubble.setFill(
                Color.web(
                        randomColor()
                )
        );

        bubble.setStroke(
                Color.web(
                        "#FFFFFF"
                )
        );

        bubble.setStrokeWidth(3);

        double x =
                50 + random.nextDouble() * 760;

        double y =
                70 + random.nextDouble() * 470;

        bubble.setLayoutX(x);
        bubble.setLayoutY(y);

        bubble.setOnMouseClicked(e -> {

            score[0]++;

            scoreLabel.setText(
                    "Score: " + score[0]
            );

            area.getChildren().remove(
                    bubble
            );
        });

        area.getChildren().add(
                bubble
        );

        FadeTransition fade =
                new FadeTransition(
                        Duration.seconds(4),
                        bubble
                );

        fade.setFromValue(1);
        fade.setToValue(0);

        fade.setOnFinished(
                e -> area.getChildren().remove(
                        bubble
                )
        );

        fade.play();
    }

    private String randomColor() {

        String[] colors = {
                "#79C9C0",
                "#83B7D8",
                "#A9A0D9",
                "#9DCB8F",
                "#E7C77B"
        };

        Random random =
                new Random();

        return colors[
                random.nextInt(
                        colors.length
                )
        ];
    }

    // ============================================================
    // GAME 4 - QUICK PUZZLE
    // ============================================================

    private void showQuickPuzzle() {

        stopAnimations();

        VBox root =
                new VBox(20);

        root.setAlignment(
                Pos.CENTER
        );

        root.setPadding(
                new Insets(40)
        );

        root.setStyle(
                "-fx-background-color:" + BG + ";"
        );

        Button back =
                linkButton(
                        "← Back to Calm Zone"
                );

        back.setOnAction(
                e -> showCalmZone()
        );

        Label title =
                text(
                        "Quick Puzzle",
                        34,
                        TEXT,
                        true
                );

        Label level =
                text(
                        "Level 1",
                        16,
                        TEAL,
                        true
                );

        Label puzzle =
                text(
                        "What number comes next?\n\n" +
                                "2   •   4   •   8   •   16   •   ?",
                        26,
                        TEXT,
                        true
                );

        TextField answer =
                new TextField();

        answer.setPromptText(
                "Enter your answer"
        );

        answer.setMaxWidth(250);

        styleField(answer);

        Button check =
                primaryButton(
                        "CHECK ANSWER"
                );

        Label result =
                text(
                        "",
                        20,
                        TEAL,
                        true
                );

        check.setOnAction(e -> {

            String value =
                    answer.getText().trim();

            if (value.equals("32")) {

                result.setText(
                        "Correct! 🎉 Excellent thinking."
                );

            } else {

                result.setText(
                        "Not quite. Hint: multiply by 2."
                );
            }
        });

        root.getChildren().addAll(
                topBar(back),
                title,
                level,
                puzzle,
                answer,
                check,
                result
        );

        setScene(
                root,
                1360,
                850
        );
    }

    // ============================================================
    // GAME 5 - COLOR CALM
    // ============================================================

    private void showColorCalm() {

        stopAnimations();

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color:" + BG + ";"
        );

        VBox center =
                new VBox(25);

        center.setAlignment(
                Pos.CENTER
        );

        center.setPadding(
                new Insets(40)
        );

        Label title =
                text(
                        "Color Calm",
                        35,
                        TEXT,
                        true
                );

        Label instruction =
                text(
                        "Choose the color that feels most calming to you.",
                        18,
                        MUTED,
                        false
                );

        StackPane preview =
                new StackPane();

        preview.setPrefSize(
                300,
                180
        );

        preview.setStyle(
                "-fx-background-color:#E9F7F5;" +
                        "-fx-background-radius:30;"
        );

        Label previewText =
                text(
                        "Your calm space",
                        20,
                        TEXT,
                        true
                );

        preview.getChildren().add(
                previewText
        );

        HBox colors =
                new HBox(18);

        colors.setAlignment(
                Pos.CENTER
        );

        String[] names = {
                "Mint",
                "Sky",
                "Lavender",
                "Sage",
                "Sunset"
        };

        String[] colorsCode = {
                "#9BE8DC",
                "#A9C7E8",
                "#C9B8E8",
                "#B8DDAF",
                "#F1D59B"
        };

        for (int i = 0; i < colorsCode.length; i++) {

            final int index = i;

            Button color =
                    new Button();

            color.setPrefSize(
                    72,
                    72
            );

            color.setStyle(
                    "-fx-background-color:" +
                            colorsCode[i] +
                            ";" +
                            "-fx-background-radius:40;" +
                            "-fx-cursor:hand;"
            );

            color.setTooltip(
                    new Tooltip(
                            names[i]
                    )
            );

            color.setOnAction(e -> {

                preview.setStyle(
                        "-fx-background-color:" +
                                colorsCode[index] +
                                ";" +
                                "-fx-background-radius:30;"
                );

                previewText.setText(
                        names[index] +
                                " feels calm ✦"
                );
            });

            colors.getChildren().add(
                    color
            );
        }

        Label note =
                text(
                        "There is no right answer — choose what feels pleasant.",
                        14,
                        MUTED,
                        false
                );

        Button back =
                linkButton(
                        "← Back to Calm Zone"
                );

        back.setOnAction(
                e -> showCalmZone()
        );

        center.getChildren().addAll(
                title,
                instruction,
                preview,
                colors,
                note,
                back
        );

        root.setCenter(center);

        setScene(
                root,
                1360,
                850
        );
    }

    // ============================================================
    // GAME 6 - FOCUS CHALLENGE
    // ============================================================

    private void showFocus() {

        stopAnimations();

        VBox root =
                new VBox(20);

        root.setAlignment(
                Pos.CENTER
        );

        root.setPadding(
                new Insets(40)
        );

        root.setStyle(
                "-fx-background-color:" + BG + ";"
        );

        Button back =
                linkButton(
                        "← Back to Calm Zone"
                );

        back.setOnAction(
                e -> showCalmZone()
        );

        Label title =
                text(
                        "Focus Challenge",
                        34,
                        TEXT,
                        true
                );

        Label instruction =
                text(
                        "Click START and wait for the button to turn green.\n" +
                                "Then click it as quickly as you can.",
                        16,
                        MUTED,
                        false
                );

        Button target =
                primaryButton(
                        "START"
                );

        target.setPrefSize(
                230,
                80
        );

        Label result =
                text(
                        "Ready?",
                        20,
                        TEAL,
                        true
        );

        final long[] startTime =
                {0};

        PauseTransition wait =
                new PauseTransition();

        target.setOnAction(e -> {

            if (target.getText().equals("START")
                    || target.getText().equals("TRY AGAIN")) {

                target.setText(
                        "WAIT..."
                );

                target.setStyle(
                        "-fx-background-color:#E7C77B;" +
                                "-fx-background-radius:30;" +
                                "-fx-font-weight:bold;" +
                                "-fx-cursor:hand;"
                );

                Random random =
                        new Random();

                double delay =
                        1.5 +
                                random.nextDouble() * 3.0;

                wait.setDuration(
                        Duration.seconds(delay)
                );

                wait.setOnFinished(
                        ev -> {

                            startTime[0] =
                                    System.nanoTime();

                            target.setText(
                                    "CLICK NOW!"
                            );

                            target.setStyle(
                                    "-fx-background-color:#78D39A;" +
                                            "-fx-background-radius:30;" +
                                            "-fx-font-weight:bold;" +
                                            "-fx-cursor:hand;"
                            );
                        }
                );

                wait.play();

            } else if (
                    target.getText().equals("CLICK NOW!")
            ) {

                long end =
                        System.nanoTime();

                double reaction =
                        (end - startTime[0])
                                / 1_000_000.0;

                result.setText(
                        String.format(
                                "Your reaction time: %.0f ms ⚡",
                                reaction
                        )
                );

                target.setText(
                        "TRY AGAIN"
                );

                target.setStyle(
                        "-fx-background-color:" +
                                MINT +
                                ";" +
                                "-fx-background-radius:30;" +
                                "-fx-font-weight:bold;" +
                                "-fx-cursor:hand;"
                );
            }
        });

        root.getChildren().addAll(
                back,
                title,
                instruction,
                target,
                result
        );

        setScene(
                root,
                1360,
                850
        );
    }

    // ============================================================
    // WEEKLY ASSESSMENT (matches Mingraud_Weekly_Wellbeing_Dashboard design)
    // ============================================================

    private static final String W_TEAL = "#1FAE96";
    private static final String W_DARK = "#0B3B4A";
    private static final String W_MUTED = "#5E7B83";
    private static final String W_CARD =
            "-fx-background-color:white;" +
            "-fx-background-radius:20;" +
            "-fx-border-color:#DCEEEC;" +
            "-fx-border-radius:20;" +
            "-fx-effect:dropshadow(gaussian, rgba(11,59,74,0.07), 16, 0, 0, 4);";

    private void showWeekly() {

        stopAnimations();

        // ---------- data (replace with database values later) ----------
        int[] scores = {14, 16, 13, 18, 20, 17, 22};

        String[] cats = {"Mood", "Stress", "Sleep", "Energy", "Focus"};
        String[] catIcons = {"😊", "⚡", "🌙", "🔋", "🎯"};
        String[] catColors = {"#2DB59B", "#F5A623", "#7B61FF", "#3B8BFF", "#FF5C93"};
        double[] catAvg = {3.6, 3.2, 3.8, 3.4, 3.1};

        double total = 0;
        for (int s : scores) total += s;
        double overall = total / scores.length;

        String level;
        String levelColor;
        String levelMessage;

        if (overall < 12) {
            level = "LOW";
            levelColor = "#1FAE96";
            levelMessage = "Your indicators look balanced. Keep up your healthy routines!";
        } else if (overall < 21) {
            level = "MODERATE";
            levelColor = "#1FAE96";
            levelMessage = "You're doing okay! A little more rest, positive habits and "
                    + "self-care can help you feel even better.";
        } else {
            level = "HIGH";
            levelColor = "#E5736B";
            levelMessage = "Several indicators suggest some extra rest may help. "
                    + "Try an activity in the Calm Zone.";
        }

        // ---------- current week (Mon - Sun) ----------
        LocalDate monday = LocalDate.now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        DateTimeFormatter shortFmt = DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH);
        DateTimeFormatter longFmt = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
        String[] dayNames = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        String[] dayDates = new String[7];
        for (int i = 0; i < 7; i++) {
            dayDates[i] = monday.plusDays(i).format(shortFmt);
        }
        String range = monday.format(longFmt) + " – " + monday.plusDays(6).format(longFmt);

        // ================= SIDEBAR =================
        VBox sidebar = new VBox(10);
        sidebar.setPrefWidth(250);
        sidebar.setMinWidth(250);
        sidebar.setPadding(new Insets(24, 18, 24, 18));
        sidebar.setStyle("-fx-background-color: linear-gradient(to bottom, #DDF4EF, #EEF9F7);");

        HBox brand = new HBox(10, logoView(120, 44));
        brand.setAlignment(Pos.CENTER_LEFT);
        if (getClass().getResource("/mingraud-logo.png") == null) {
            brand.getChildren().setAll(text("Mingraud", 22, W_DARK, true));
        }

        Circle avatar = new Circle(26, Color.web(W_TEAL));
        Label avatarIcon = text("👤", 22, "#FFFFFF", false);
        StackPane avatarPane = new StackPane(avatar, avatarIcon);

        VBox hello = new VBox(2,
                text("Hello,", 14, W_MUTED, false),
                text(userName, 20, W_DARK, true));
        hello.setAlignment(Pos.CENTER_LEFT);

        HBox profileRow = new HBox(12, avatarPane, hello);
        profileRow.setAlignment(Pos.CENTER_LEFT);
        profileRow.setPadding(new Insets(16, 0, 18, 0));

        Button navDashboard = weeklyNavButton("⌂   Dashboard", false, e -> showDashboard());
        Button navCheckin = weeklyNavButton("♡   Daily Check-in", false, e -> showAssessment());
        Button navCalm = weeklyNavButton("☾   Calm Zone", false, e -> showCalmZone());
        Button navWeekly = weeklyNavButton("▦   Weekly Assessment", true, e -> showWeekly());
        Button navLogout = weeklyNavButton("←   Logout", false, e -> showWelcome());

        Region sideSpacer = new Region();
        VBox.setVgrow(sideSpacer, Priority.ALWAYS);

        Label tagline = text("Better Mind\nBrighter You", 16, "#6FA8A0", false);
        tagline.setTextAlignment(TextAlignment.CENTER);
        tagline.setStyle("-fx-font-style: italic;");
        VBox taglineBox = new VBox(tagline);
        taglineBox.setAlignment(Pos.CENTER);
        taglineBox.setPadding(new Insets(0, 0, 16, 0));

        sidebar.getChildren().addAll(
                brand, profileRow,
                navDashboard, navCheckin, navCalm, navWeekly,
                sideSpacer, taglineBox, navLogout);

        // ================= HEADER =================
        Label title = text("Weekly Assessment", 30, W_DARK, true);
        Label subtitle = text("Track your wellbeing progress over the past week", 15, W_MUTED, false);
        VBox titleBox = new VBox(2, title, subtitle);

        Label datePill = text("📅   " + range + "   ⌄", 14, W_DARK, false);
        datePill.setPadding(new Insets(12, 18, 12, 18));
        datePill.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:14;" +
                "-fx-border-color:#DCEEEC;" +
                "-fx-border-radius:14;");

        Region headerSpacer = new Region();
        HBox.setHgrow(headerSpacer, Priority.ALWAYS);
        HBox header = new HBox(titleBox, headerSpacer, datePill);
        header.setAlignment(Pos.CENTER_LEFT);

        // ================= TREND CARD =================
        Label trendTitle = text("Wellbeing Score Trend", 19, W_DARK, true);

        Canvas trendCanvas = new Canvas();
        Pane trendHolder = new Pane(trendCanvas);
        trendCanvas.setManaged(false);
        trendHolder.setMinHeight(240);
        trendHolder.setPrefHeight(260);
        trendCanvas.widthProperty().bind(trendHolder.widthProperty());
        trendCanvas.heightProperty().bind(trendHolder.heightProperty());

        Runnable redrawTrend = () ->
                drawWeeklyTrend(trendCanvas, scores, dayNames, dayDates);
        trendCanvas.widthProperty().addListener((o, a, b) -> redrawTrend.run());
        trendCanvas.heightProperty().addListener((o, a, b) -> redrawTrend.run());

        VBox trendCard = new VBox(8, trendTitle, trendHolder);
        trendCard.setPadding(new Insets(20, 22, 12, 22));
        trendCard.setStyle(W_CARD);
        VBox.setVgrow(trendHolder, Priority.ALWAYS);

        // ================= CATEGORY AVERAGES =================
        Label catTitle = text("Category Averages", 19, W_DARK, true);
        VBox catRows = new VBox(14);
        catRows.setPadding(new Insets(10, 0, 0, 0));

        for (int i = 0; i < cats.length; i++) {

            Label iconLabel = text(catIcons[i], 20, "#FFFFFF", false);
            StackPane iconBox = new StackPane(iconLabel);
            iconBox.setPrefSize(44, 44);
            iconBox.setMinSize(44, 44);
            iconBox.setMaxSize(44, 44);
            iconBox.setStyle("-fx-background-color:" + catColors[i] + ";-fx-background-radius:13;");

            Label name = text(cats[i], 16, W_DARK, false);
            name.setPrefWidth(70);
            name.setMinWidth(70);

            StackPane track = new StackPane();
            track.setAlignment(Pos.CENTER_LEFT);
            track.setPrefHeight(10);
            track.setMinHeight(10);
            track.setMaxHeight(10);
            track.setStyle("-fx-background-color:#E8EFF0;-fx-background-radius:6;");
            HBox.setHgrow(track, Priority.ALWAYS);

            Region fill = new Region();
            fill.setMinHeight(10);
            fill.setPrefHeight(10);
            fill.setMaxHeight(10);
            fill.maxWidthProperty().bind(track.widthProperty().multiply(catAvg[i] / 5.0));
            fill.setStyle("-fx-background-color:" + catColors[i] + ";-fx-background-radius:6;");
            track.getChildren().add(fill);

            Label value = text(String.format("%.1f / 5", catAvg[i]), 15, W_DARK, true);
            value.setPrefWidth(70);
            value.setMinWidth(70);
            value.setAlignment(Pos.CENTER_RIGHT);

            HBox row = new HBox(16, iconBox, name, track, value);
            row.setAlignment(Pos.CENTER_LEFT);
            catRows.getChildren().add(row);
        }

        VBox catCard = new VBox(6, catTitle, catRows);
        catCard.setPadding(new Insets(20, 24, 20, 24));
        catCard.setStyle(W_CARD);
        HBox.setHgrow(catCard, Priority.ALWAYS);
        catCard.setMaxWidth(Double.MAX_VALUE);

        // ================= OVERALL SCORE =================
        Label leaf = text("🍃", 20, W_TEAL, false);
        StackPane leafBox = new StackPane(leaf);
        leafBox.setPrefSize(46, 46);
        leafBox.setMinSize(46, 46);
        leafBox.setMaxSize(46, 46);
        leafBox.setStyle("-fx-background-color:#D3F1EA;-fx-background-radius:23;");

        HBox scoreHeader = new HBox(12, leafBox, text("Overall Wellbeing Score", 19, W_DARK, true));
        scoreHeader.setAlignment(Pos.CENTER_LEFT);

        Canvas gaugeCanvas = new Canvas(230, 175);
        drawWeeklyGauge(gaugeCanvas, overall, 30, levelColor);

        Label scoreValue = text(String.format("%.1f", overall), 40, W_DARK, true);
        Label scoreMax = text("/ 30", 16, W_MUTED, false);
        VBox scoreText = new VBox(0, scoreValue, scoreMax);
        scoreText.setAlignment(Pos.CENTER);
        scoreText.setTranslateY(-4);

        StackPane gauge = new StackPane(gaugeCanvas, scoreText);
        gauge.setMaxSize(230, 175);

        Label badge = text(level, 15, "#FFFFFF", true);
        badge.setPadding(new Insets(6, 26, 6, 26));
        badge.setStyle("-fx-background-color:" + levelColor + ";-fx-background-radius:16;");

        Label scoreMessage = text(levelMessage, 14, W_MUTED, false);
        scoreMessage.setWrapText(true);
        scoreMessage.setTextAlignment(TextAlignment.CENTER);
        scoreMessage.setMaxWidth(300);

        VBox scoreCard = new VBox(10, scoreHeader, gauge, badge, scoreMessage);
        scoreCard.setAlignment(Pos.TOP_CENTER);
        scoreCard.setPadding(new Insets(20, 22, 20, 22));
        scoreCard.setPrefWidth(380);
        scoreCard.setMinWidth(340);
        scoreCard.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #EAF8F5, #F4FBFA);" +
                "-fx-background-radius:20;" +
                "-fx-border-color:#DCEEEC;" +
                "-fx-border-radius:20;");

        HBox bottomRow = new HBox(18, catCard, scoreCard);

        // ================= CONTENT =================
        VBox content = new VBox(18, header, trendCard, bottomRow);
        content.setPadding(new Insets(26, 30, 26, 30));
        content.setStyle("-fx-background-color: #F4FBFA;");

        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background:#F4FBFA;-fx-background-color:#F4FBFA;");

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color:#F4FBFA;");
        root.setLeft(sidebar);
        root.setCenter(scroll);

        setScene(root, 1360, 850);
        redrawTrend.run();
    }

    // ---------- line / area chart ----------
    private void drawWeeklyTrend(Canvas c, int[] scores, String[] names, String[] dates) {

        GraphicsContext g = c.getGraphicsContext2D();
        double w = c.getWidth();
        double h = c.getHeight();

        g.clearRect(0, 0, w, h);

        if (w < 120 || h < 120) return;

        final int maxY = 30;
        double left = 46, right = 24, top = 34, bottom = 46;
        double pw = w - left - right;
        double ph = h - top - bottom;
        double baseY = top + ph;
        int n = scores.length;

        g.setFont(Font.font("System", 12));

        // horizontal grid + y labels
        for (int v = 0; v <= maxY; v += 5) {
            double y = baseY - ph * v / maxY;
            g.setStroke(Color.web("#E6F0EF"));
            g.setLineWidth(1);
            g.strokeLine(left, y, left + pw, y);
            g.setFill(Color.web(W_MUTED));
            g.setTextAlign(TextAlignment.RIGHT);
            g.fillText(String.valueOf(v), left - 10, y + 4);
        }

        // point coordinates
        double[] xs = new double[n];
        double[] ys = new double[n];
        for (int i = 0; i < n; i++) {
            xs[i] = left + 24 + (pw - 48) * i / (n - 1);
            ys[i] = baseY - ph * scores[i] / maxY;
        }

        // vertical grid + day labels
        g.setTextAlign(TextAlignment.CENTER);
        for (int i = 0; i < n; i++) {
            g.setStroke(Color.web("#EEF5F4"));
            g.strokeLine(xs[i], top, xs[i], baseY);
            g.setFill(Color.web(W_MUTED));
            g.fillText(names[i], xs[i], baseY + 20);
            g.fillText(dates[i], xs[i], baseY + 37);
        }

        // axes
        g.setStroke(Color.web("#CFE2E0"));
        g.setLineWidth(1.2);
        g.strokeLine(left, top, left, baseY);
        g.strokeLine(left, baseY, left + pw, baseY);

        // area fill
        g.setFill(new LinearGradient(0, top, 0, baseY, false, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web(W_TEAL, 0.28)),
                new Stop(1, Color.web(W_TEAL, 0.02))));
        g.beginPath();
        g.moveTo(xs[0], baseY);
        for (int i = 0; i < n; i++) g.lineTo(xs[i], ys[i]);
        g.lineTo(xs[n - 1], baseY);
        g.closePath();
        g.fill();

        // line
        g.setStroke(Color.web(W_TEAL));
        g.setLineWidth(3);
        g.setLineCap(StrokeLineCap.ROUND);
        g.beginPath();
        g.moveTo(xs[0], ys[0]);
        for (int i = 1; i < n; i++) g.lineTo(xs[i], ys[i]);
        g.stroke();

        // points + value bubbles
        for (int i = 0; i < n; i++) {

            g.setFill(Color.web(W_TEAL));
            g.fillOval(xs[i] - 5, ys[i] - 5, 10, 10);

            double bw = 34, bh = 24;
            double bx = xs[i] - bw / 2;
            double by = ys[i] - 14 - bh;

            g.setFill(Color.web("#E6F8F4"));
            g.fillRoundRect(bx, by, bw, bh, 8, 8);
            g.setStroke(Color.web("#BFE9DF"));
            g.setLineWidth(1);
            g.strokeRoundRect(bx, by, bw, bh, 8, 8);

            g.setFill(Color.web("#12806D"));
            g.setFont(Font.font("System", FontWeight.BOLD, 13));
            g.setTextAlign(TextAlignment.CENTER);
            g.fillText(String.valueOf(scores[i]), xs[i], by + 17);
        }
    }

    // ---------- semicircle gauge ----------
    private void drawWeeklyGauge(Canvas c, double value, double max, String color) {

        GraphicsContext g = c.getGraphicsContext2D();
        double cx = c.getWidth() / 2;
        double cy = 100;
        double r = 80;

        g.clearRect(0, 0, c.getWidth(), c.getHeight());
        g.setLineWidth(15);
        g.setLineCap(StrokeLineCap.ROUND);

        // track (270 degrees, opening at the bottom)
        g.setStroke(Color.web("#DCEBE8"));
        g.strokeArc(cx - r, cy - r, r * 2, r * 2, 225, -270, ArcType.OPEN);

        // value
        double ratio = Math.max(0, Math.min(1, value / max));
        g.setStroke(Color.web(color));
        g.strokeArc(cx - r, cy - r, r * 2, r * 2, 225, -270 * ratio, ArcType.OPEN);
    }

    private Button weeklyNavButton(
            String label,
            boolean active,
            javafx.event.EventHandler<javafx.event.ActionEvent> action
    ) {
        Button b = new Button(label);
        b.setMaxWidth(Double.MAX_VALUE);
        b.setAlignment(Pos.CENTER_LEFT);
        b.setFont(Font.font("System", FontWeight.BOLD, 15));
        b.setStyle(
                "-fx-background-color:" + (active ? "#CDEFE8" : "transparent") + ";" +
                "-fx-text-fill:" + (active ? "#12806D" : W_DARK) + ";" +
                "-fx-background-radius:12;" +
                "-fx-padding:13 16;" +
                "-fx-cursor:hand;");
        b.setOnAction(action);
        return b;
    }

    // ---------- logo helpers ----------
    private ImageView logoView(double fitWidth, double fitHeight) {
        ImageView view = new ImageView();
        URL url = getClass().getResource("/mingraud-logo.png");
        if (url != null) {
            view.setImage(new Image(url.toExternalForm()));
        }
        view.setFitWidth(fitWidth);
        view.setFitHeight(fitHeight);
        view.setPreserveRatio(true);
        return view;
    }

    // back button on the left, Mingraud logo on the right
    private HBox topBar(Button back) {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox bar = new HBox(10, back, spacer, logoView(150, 56));
        bar.setAlignment(Pos.CENTER_LEFT);
        bar.setMaxWidth(Double.MAX_VALUE);
        return bar;
    }

    // ============================================================
    // UI COMPONENTS
    // ============================================================

    private Label text(
            String value,
            double size,
            String color,
            boolean bold
    ) {

        Label label =
                new Label(value);

        label.setFont(
                Font.font(
                        "System",
                        bold
                                ? FontWeight.BOLD
                                : FontWeight.NORMAL,
                        size
                )
        );

        label.setTextFill(
                Color.web(color)
        );

        return label;
    }

    private Button primaryButton(
            String value
    ) {

        Button button =
                new Button(value);

        button.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        15
                )
        );

        button.setTextFill(
                Color.web(NAVY)
        );

        button.setPrefHeight(
                54
        );

        button.setPadding(
                new Insets(
                        0,
                        28,
                        0,
                        28
                )
        );

        button.setStyle(
                "-fx-background-color:" +
                        MINT +
                        ";" +
                        "-fx-background-radius:30;" +
                        "-fx-cursor:hand;"
        );

        return button;
    }

    private Button linkButton(
            String value
    ) {

        Button button =
                new Button(value);

        button.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        14
                )
        );

        button.setTextFill(
                Color.web(TEAL)
        );

        button.setStyle(
                "-fx-background-color:transparent;" +
                        "-fx-cursor:hand;"
        );

        return button;
    }

    private Button sideButton(
            String value
    ) {

        Button button =
                new Button(value);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setPadding(
                new Insets(14)
        );

        button.setTextFill(
                Color.web("#D8E8E9")
        );

        button.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        14
                )
        );

        button.setStyle(
                "-fx-background-color:transparent;" +
                        "-fx-background-radius:12;" +
                        "-fx-cursor:hand;"
        );

        return button;
    }

    private void styleField(
            TextInputControl field
    ) {

        field.setPrefHeight(
                54
        );

        field.setFont(
                Font.font(
                        "System",
                        15
                )
        );

        field.setPadding(
                new Insets(
                        0,
                        18,
                        0,
                        18
                )
        );

        field.setStyle(
                "-fx-background-color:#F6FAFA;" +
                        "-fx-background-radius:14;" +
                        "-fx-border-color:" +
                        BORDER +
                        ";" +
                        "-fx-border-radius:14;"
        );
    }

    private VBox dashboardCard(
            String small,
            String title,
            String description,
            String buttonText,
            javafx.event.EventHandler<javafx.event.ActionEvent> action
    ) {

        VBox card =
                new VBox(12);

        card.setPrefWidth(
                300
        );

        card.setPadding(
                new Insets(24)
        );

        card.setStyle(
                "-fx-background-color:white;" +
                        "-fx-background-radius:20;" +
                        "-fx-border-color:" +
                        BORDER +
                        ";"
        );

        Label smallLabel =
                text(
                        small,
                        12,
                        TEAL,
                        true
                );

        Label titleLabel =
                text(
                        title,
                        21,
                        TEXT,
                        true
                );

        Label descriptionLabel =
                text(
                        description,
                        14,
                        MUTED,
                        false
                );

        descriptionLabel.setWrapText(
                true
        );

        Button button =
                linkButton(
                        buttonText + "  →"
                );

        button.setOnAction(
                action
        );

        card.getChildren().addAll(
                smallLabel,
                titleLabel,
                descriptionLabel,
                button
        );

        return card;
    }

    private VBox activityCard(
            String icon,
            String title,
            String description,
            javafx.event.EventHandler<javafx.event.ActionEvent> action
    ) {

        VBox card =
                new VBox(10);

        card.setPrefWidth(
                395
        );

        card.setPrefHeight(
                230
        );

        card.setPadding(
                new Insets(22)
        );

        card.setStyle(
                "-fx-background-color:white;" +
                        "-fx-background-radius:22;" +
                        "-fx-border-color:" +
                        BORDER +
                        ";"
        );

        Label iconLabel =
                text(
                        icon,
                        34,
                        TEAL,
                        true
                );

        Label titleLabel =
                text(
                        title,
                        20,
                        TEXT,
                        true
                );

        Label desc =
                text(
                        description,
                        14,
                        MUTED,
                        false
                );

        desc.setWrapText(
                true
        );

        Button play =
                primaryButton(
                        "PLAY   →"
                );

        play.setPrefHeight(
                45
        );

        play.setOnAction(
                action
        );

        card.getChildren().addAll(
                iconLabel,
                titleLabel,
                desc,
                play
        );

        return card;
    }

    

private VBox ratingQuestion(
        String title,
        String[] options,
        int[] score
) {
   VBox box = new VBox(5);

    Label heading = new Label(title);
heading.setFont(Font.font("System", FontWeight.BOLD, 16));
heading.setStyle("-fx-text-fill: #000000;");
heading.setWrapText(true);
    HBox choices = new HBox(8);
    choices.setAlignment(Pos.CENTER_LEFT);
    choices.setMaxWidth(Double.MAX_VALUE);

    Button[] buttons = new Button[options.length];

    String normalStyle =
        "-fx-background-color: #F7EAF2;" +
        "-fx-text-fill: #000000;" +
        "-fx-background-radius: 12;" +
        "-fx-padding: 10 12;" +
        "-fx-cursor: hand;";

String selectedStyle =
        "-fx-background-color: #63C9BE;" +
        "-fx-text-fill: #000000;" +
        "-fx-background-radius: 12;" +
        "-fx-padding: 10 12;" +
        "-fx-cursor: hand;";

    for (int i = 0; i < options.length; i++) {
        final int value = i;

        Button button = new Button(options[i]);
        buttons[i] = button;
        button.setMinHeight(42);

        if (options[i].length() <= 2) {
            button.setPrefWidth(48);
        } else {
            button.setPrefWidth(105);
        }

        button.setStyle(i == 0 ? selectedStyle : normalStyle);

        button.setOnAction(e -> {
            score[0] = value;

            for (int j = 0; j < buttons.length; j++) {
                buttons[j].setStyle(
                        j == value ? selectedStyle : normalStyle
                );
            }
        });

        choices.getChildren().add(button);
    }

    box.getChildren().addAll(heading, choices);
    return box;
}

private Slider slider(
        double min,
        double max,
        double value
) {
    Slider slider = new Slider(min, max, value);

    slider.setShowTickLabels(true);
    slider.setShowTickMarks(true);
    slider.setMajorTickUnit(1);
    slider.setMinorTickCount(0);
    slider.setBlockIncrement(1);
    slider.setSnapToTicks(true);
    slider.setPrefWidth(650);
    slider.setMinHeight(42);

    slider.setStyle(
            "-fx-control-inner-background: #F6DCE9;" +
            "-fx-accent: #63C9BE;" +
            "-fx-focus-color: transparent;" +
            "-fx-faint-focus-color: transparent;"
    );

    return slider;
}

    private Label valueLabel(
            Slider slider
    ) {

        Label label =
                text(
                        String.format(
                                "%.0f",
                                slider.getValue()
                        ),
                        15,
                        TEAL,
                        true
                );

        slider.valueProperty()
                .addListener(
                        (obs, oldValue, newValue) ->

                                label.setText(
                                        String.format(
                                                "%.0f",
                                                newValue.doubleValue()
                                        )
                                )
                );

        return label;
    }

    private VBox question(
            String title,
            Slider slider,
            Label value
    ) {

        VBox box =
                new VBox(8);

        Label label =
                text(
                        title,
                        16,
                        TEXT,
                        true
                );

        HBox line =
                new HBox(
                        15,
                        slider,
                        value
                );

        line.setAlignment(
                Pos.CENTER_LEFT
        );

        box.getChildren().addAll(
                label,
                line
        );

        return box;
    }

    // ============================================================
    // PREMIUM LOGO
    // ============================================================

    private void drawMind(
            GraphicsContext gc
    ) {

        double cx = 235;
        double cy = 235;

        gc.setStroke(
                Color.web("#4DB7B5")
        );

        gc.setLineWidth(2);

        for (
                int r = 80;
                r <= 190;
                r += 38
        ) {

            gc.strokeOval(
                    cx - r,
                    cy - r,
                    r * 2,
                    r * 2
            );
        }

        gc.setFill(
                Color.web(MINT)
        );

        gc.fillOval(
                cx - 34,
                cy - 34,
                68,
                68
        );

        gc.setFill(
                Color.web(NAVY)
        );

        gc.fillOval(
                cx - 20,
                cy - 8,
                12,
                12
        );

        gc.fillOval(
                cx + 8,
                cy - 8,
                12,
                12
        );

        gc.setStroke(
                Color.web(NAVY)
        );

        gc.setLineWidth(5);

        gc.strokeArc(
                cx - 22,
                cy - 4,
                44,
                32,
                200,
                140,
                javafx.scene.shape.ArcType.OPEN
        );

        gc.setFill(
                Color.web(MINT)
        );

        gc.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        15
                )
        );

        gc.fillText(
                "PAUSE  •  BREATHE  •  RESET",
                cx - 100,
                cy + 105
        );
    }

    private void drawSmallMind(
            GraphicsContext gc
    ) {

        double cx = 125;
        double cy = 125;

        gc.setStroke(
                Color.web(MINT)
        );

        gc.setLineWidth(5);

        gc.strokeOval(
                cx - 37,
                cy - 37,
                74,
                74
        );

        gc.setFill(
                Color.web(MINT)
        );

        gc.fillOval(
                cx - 33,
                cy - 33,
                66,
                66
        );

        gc.setFill(
                Color.web(NAVY)
        );

        gc.fillOval(
                cx - 20,
                cy - 8,
                13,
                13
        );

        gc.fillOval(
                cx + 8,
                cy - 8,
                13,
                13
        );

        gc.setStroke(
                Color.web(NAVY)
        );

        gc.setLineWidth(4);

        gc.strokeArc(
                cx - 20,
                cy - 2,
                40,
                30,
                200,
                140,
                javafx.scene.shape.ArcType.OPEN
        );
    }

    // ============================================================
    // STOP ANIMATIONS
    // ============================================================

    private void stopAnimations() {

        if (breathingTimeline != null) {

            breathingTimeline.stop();

            breathingTimeline = null;
        }

        if (popTimeline != null) {

            popTimeline.stop();

            popTimeline = null;
        }
    }

    // ============================================================
    // ALERT
    // ============================================================

    private void showAlert(
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                "Mingraud"
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }

    // ============================================================
    // SCENE
    // ============================================================

    
private void setScene(
        javafx.scene.Parent root,
        double width,
        double height
) {

    Scene scene = new Scene(root, width, height);

    stage.setScene(scene);
    stage.setWidth(width);
    stage.setHeight(height);
    stage.centerOnScreen();
}

    // ============================================================
    // MAIN
    // ============================================================

    public static void main(
            String[] args
    ) {

        launch(args);
    }
}
