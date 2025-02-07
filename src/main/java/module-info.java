module ec.edu.uees.spotifyapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens ec.edu.uees.spotifyapp to javafx.fxml;
    exports ec.edu.uees.spotifyapp;
}
