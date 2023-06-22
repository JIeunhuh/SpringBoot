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
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private Long seq;
	private String title;
	private String writer;
	//@Column(length = 10)
	private String content;
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date createDate;
	private Long cnt;
}
