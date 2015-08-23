package com.lcweb.commons;

import java.io.File;
import java.util.List;

public class CKEditor {
	private List<String> directionPath = null;
	private static CKEditor ckeditor = null;

	private CKEditor() {
	}

	public static synchronized CKEditor getInstance() {
		if (ckeditor == null) {
			ckeditor = new CKEditor();
		}
		return ckeditor;
	}

	public List<String> getDirectionPath() {
		return directionPath;
	}

	public void setDirectionPath(List<String> directionPath) {
		this.directionPath = directionPath;
	}

	public boolean removeAttachment(String filePath) {
		File file = new File(filePath);
		if (file.exists() && file.isFile()) {
			try {
				return file.delete();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	public void init(){
		if(directionPath!=null){
			directionPath.clear();
		}
	}
}
