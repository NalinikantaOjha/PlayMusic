package Model;


import com.google.gson.annotations.SerializedName;
import java.io.Serializable;


public class ComingSoonModel implements Serializable {

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	@SerializedName("year")
	private String year;

	@SerializedName("contentRating")
	private String contentRating;

	@SerializedName("duration")
	private String duration;

	@SerializedName("releaseDate")
	private String releaseDate;

	@SerializedName("averageRating")
	private int averageRating;

	@SerializedName("originalTitle")
	private String originalTitle;

	@SerializedName("storyline")
	private String storyline;

	@SerializedName("imdbRating")
	private String imdbRating;

	@SerializedName("posterurl")
	private String posterurl;

	@SerializedName("video_url")
	private String videoUrl;

	public String getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getYear(){
		return year;
	}

	public String getContentRating(){
		return contentRating;
	}

	public String getDuration(){
		return duration;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public int getAverageRating(){
		return averageRating;
	}

	public String getOriginalTitle(){
		return originalTitle;
	}

	public String getStoryline(){
		return storyline;
	}

	public String getImdbRating(){
		return imdbRating;
	}

	public String getPosterurl(){
		return posterurl;
	}

	public String getVideoUrl(){
		return videoUrl;
	}

	@Override
 	public String toString(){
		return 
			"ComingSoonModel{" + 
			"id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",year = '" + year + '\'' + 
			",contentRating = '" + contentRating + '\'' + 
			",duration = '" + duration + '\'' + 
			",releaseDate = '" + releaseDate + '\'' + 
			",averageRating = '" + averageRating + '\'' + 
			",originalTitle = '" + originalTitle + '\'' + 
			",storyline = '" + storyline + '\'' + 
			",imdbRating = '" + imdbRating + '\'' + 
			",posterurl = '" + posterurl + '\'' + 
			",video_url = '" + videoUrl + '\'' + 
			"}";
		}
}