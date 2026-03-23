/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.bytespace.Super;

 //https://stackoverflow.com/questions/14353302/displaying-image-in-java

import business.bytespace.Comment;
import business.bytespace.Image;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author raren
 */
public class Post implements Serializable {
        private Integer postID;
        private String postText;
        private HashMap<Integer, Image> images;
        private HashMap<Integer, Comment> comments;

        //images will be pulled from the file system and saved to the file system
        // the database will be used to store the image paths
        // NOTE: if we want to image icon paths we can do that too.

        public Post(){
            postID = null;
            postText = null;
            images = null;
            comments = null;
        }

        public Post(Integer postID, String postText) {
            this.postID = postID;
            this.postText = postText;
        }

        public Post(Integer postID, String postText, HashMap<Integer, Comment> comments) {
            this.postID = postID;
            this.postText = postText;
            this.comments = comments;
        }
        
        public Post(Integer postID, String postText, HashMap<Integer, Image> images, HashMap<Integer, Comment> comments) {
            this.postID = postID;
            this.postText = postText;
            this.images = images;
            this.comments = comments;
        }

        public Integer getPostID() {
            return postID;
        }

        public void setPostID(Integer postID) {
            this.postID = postID;
        }

        public String getPostText() {
            return postText;
        }

        public void setPostText(String postText) {
            this.postText = postText;
        }

        public HashMap<Integer, Image> getImages() {
            return images;
        }

        public void setImages(HashMap<Integer, Image> images) {
            this.images = images;
        }

        public HashMap<Integer, Comment> getComments() {
            return comments;
        }

        public void setComments(HashMap<Integer, Comment> comments) {
            this.comments = comments;
        }
        

}

