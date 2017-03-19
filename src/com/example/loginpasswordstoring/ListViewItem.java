package com.example.loginpasswordstoring;

public class ListViewItem implements Comparable<ListViewItem> {

	String url, id, password;

	public ListViewItem(String url, String id, String password) {
		super();
		this.url = url;
		this.id = id;
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public int compareTo(ListViewItem another) {
		return this.getUrl().compareTo(another.getUrl()) ;
	}
	
}
