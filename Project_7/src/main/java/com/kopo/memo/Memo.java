package com.kopo.memo;

public class Memo {
	int idx;
	String title;
	String writer;
	String content;
	String created;
	String updated;
	int userIdx;
	
	Memo() {
		
	}
	
	Memo(String new_title, String writer, String new_content, String first_created, String now, int userIdx) {
		this.title = new_title;
		this.writer = writer;
		this.content = new_content;
		this.created = first_created;
		this.updated = now;
		this.userIdx = userIdx;
	}
	
	// update시 사용
	Memo(int idx, String new_title, String new_content, String updated) {
		this.idx = idx;
		this.title = new_title;
		this.content = new_content;
		this.updated = updated;
	}

	@Override
	public String toString() {
		return "Memo [idx=" + idx + ", title=" + title + ", writer=" + writer + ", content=" + content + ", created="
				+ created + ", updated=" + updated + ", userIdx=" + userIdx + "]";
	}
}
