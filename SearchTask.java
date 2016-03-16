package new_Word.搜索文件;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class SearchTask implements Runnable{
	public BlockingQueue<File> queue;
	 private String keyword;
	 public SearchTask(BlockingQueue<File>queue,String keyword) {
		this.queue=queue;
		this.keyword=keyword;
	}
	@Override
	public void run() {
		boolean done=false;
		while(!done){
			File file = null;
			try {
				file = queue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(file==FileEnumerationTask.dummy){
				try {
					queue.put(file);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				done=true;
			} else
				try {
					search(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
	}
	/**
	 * 搜寻文件
	 * @param file
	 * @throws IOException
	 */
	public void search(File file) throws IOException{
		Scanner in=new Scanner(new FileInputStream(file));
		int lineNumber =0;
		while(in.hasNextLine()){
			lineNumber++;
			String line=in.nextLine();
			if(line.contains(keyword)){
				System.out.printf("%s:%d:%n",file.getPath(),lineNumber,line);
			}
		}
		in.close();
	}
}
