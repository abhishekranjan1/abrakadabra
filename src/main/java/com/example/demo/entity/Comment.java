package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private long id;
 	
 	@Column(name = "username")
 	private String userName;
 	
 	@Column(name = "profile_image")
 	private String profileImage;
 	
 	@Column(name = "comment_text")
 	private String commentText;
 	
 	@Column(name = "timestamp")
 	private Date timeStamp;

}
