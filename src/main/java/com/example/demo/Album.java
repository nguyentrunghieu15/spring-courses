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

	public static class Builder {
		private int Id = 0;
		private int userId = 0;
		private String title = "";

		public Builder() {
			super();
		}

		public Builder id(int Id) {
			this.Id = Id;
			return this;
		}

		public Builder userId(int userId) {
			this.userId = userId;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Album build() {
			return new Album(this.Id, this.userId, this.title);
		}

	}

	public static Builder createBuilder() {
		return new Builder();
	}
}
