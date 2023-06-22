package edu.pnu.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Board {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long seq;
	private String title;
	private String writer;
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	private long cnt;
}
