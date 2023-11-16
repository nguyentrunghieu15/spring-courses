/**
 * 
 */
package com.example.demo;

/**
 * 
 */
public class Album {

	/**
	 * 
	 */
	private int Id;
	private int userId;
	private String title;

	public Album(int id, int userId, String title) {
		super();
		Id = id;
		this.userId = userId;
		this.title = title;
	}

	private class Builder {
		private int Id = 0;
		private int userId = 0;
		private String title = "";

		public Builder() {
			super();
		}

		public void setId(int Id) {
			this.Id = Id;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Album build() {
			return new Album(this.Id, this.userId, this.title);
		}

	}

	public Builder createBuilder() {
		return new Builder();
	}
}
