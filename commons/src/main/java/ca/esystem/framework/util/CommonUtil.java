package ca.esystem.framework.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtil {
    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	
	public static Date convertStringtoDate(String dateStr,String pattern){
		 SimpleDateFormat dateformat = new SimpleDateFormat(pattern);  //such as yyyy-MM-dd
		 Date newdate = null;
		 try{
			 if(dateStr!=null&&dateStr.length()>=8)
		         newdate = dateformat.parse(dateStr);
		 }catch(Exception e){
			 logger.error(e.toString());
			 System.err.println(e);
		 }
		 return newdate;
	}
	
	public static String convertDatetoString(Date date, String pattern){
		 if(date==null) return "";
		 SimpleDateFormat dateformat = new SimpleDateFormat(pattern);  //such as yyyy-MM-dd
		 String datestr = null;
		 try{
			 datestr = dateformat.format(date);
		 }catch(Exception e){
			System.err.println(e);
		 }
		 return datestr;
	}
	
	public static boolean isNotEmptyStr(String str){
		boolean result=true;
		if(str==null){
		   result = false;
		}else{
			if(str.trim().length()==0){
				result=false;
			}else{
				result=true;
			}
		}
		return result;
	}
	
	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	} 
	
    public static boolean ipIsInNet(String iparea, String ip) {   
        if (iparea == null)   
            throw new NullPointerException("Ip Area has not been specified.");   
        if (ip == null)   
            throw new NullPointerException("IP Address can not be empty");   
        iparea = iparea.trim();   
        ip = ip.trim();   
        final String REGX_IP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";   
        final String REGX_IPB = REGX_IP + "\\-" + REGX_IP;   
        if (!iparea.matches(REGX_IPB) || !ip.matches(REGX_IP))   
            return false;   
        int idx = iparea.indexOf('-');   
        String[] sips = iparea.substring(0, idx).split("\\.");   
        String[] sipe = iparea.substring(idx + 1).split("\\.");   
        String[] sipt = ip.split("\\.");   
        long ips = 0L, ipe = 0L, ipt = 0L;   
        for (int i = 0; i < 4; ++i) {   
            ips = ips << 8 | Integer.parseInt(sips[i]);   
            ipe = ipe << 8 | Integer.parseInt(sipe[i]);   
            ipt = ipt << 8 | Integer.parseInt(sipt[i]);   
        }   
        if (ips > ipe) {   
            long t = ips;   
            ips = ipe;   
            ipe = t;   
        }   
        return ips <= ipt && ipt <= ipe;   
    }	
    
    public static String getRandomString(int digital){
    	String randomstr="";
    	Random r = new Random();
    	for(int i=0;i<digital;i++){
    	   randomstr+=(char)('a' + r.nextInt(26));
    	}
    	return randomstr;
    }
    
    public static String getRandomString(int digital,char start,int scope){
    	String randomstr="";
    	Random r = new Random();
    	for(int i=0;i<digital;i++){
    	   randomstr+=(char)(start + r.nextInt(scope));
    	}
    	return randomstr;
    }    
    
    public static boolean isValidEmail(String email){  
        boolean valid = false;
        Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}");  
        Matcher m = p.matcher(email);  
        boolean b = m.matches();
        if(b) {  
            System.out.println("有效邮件地址");  
            valid=true;  
        }
        return valid;  
    }  
    
	public Map<String,String> getUserAgent(HttpServletRequest req){  
        Map<String,String> Sys= new HashMap<String, String>();  
        String ua = req.getHeader("User-Agent").toLowerCase();  
        String s;  
        String msieP = "msie ([\\d.]+)";  
        String firefoxP = "firefox\\/([\\d.]+)";  
        String chromeP = "chrome\\/([\\d.]+)";  
        String operaP = "opera.([\\d.]+)/)";  
        String safariP = "version\\/([\\d.]+).*safari";  
          
        Pattern pattern = Pattern.compile(msieP);  
        Matcher mat = pattern.matcher(ua);  
        if(mat.find()){  
            s=mat.group();  
            Sys.put("type", "ie");  
            Sys.put("version", s.split(" ")[1]);  
            return Sys;  
        }  
        pattern = Pattern.compile(firefoxP);  
        mat=pattern.matcher(ua);  
        if(mat.find()){  
            s=mat.group();  
            System.out.println(s);  
            Sys.put("type", "firefox");  
            Sys.put("version", s.split("/")[1]);  
            return Sys;  
        }  
        pattern = Pattern.compile(chromeP);  
        mat=pattern.matcher(ua);  
        if(mat.find()){  
            s=mat.group();  
            Sys.put("type", "chrome");  
            Sys.put("version", s.split("/")[1]);  
            return Sys;  
        }   
        pattern = Pattern.compile(operaP);  
        mat=pattern.matcher(ua);  
        if(mat.find()){  
            s=mat.group();  
            Sys.put("type", "opera");  
            Sys.put("version", s.split("\\.")[1]);  
            return Sys;  
        }   
        pattern = Pattern.compile(safariP);  
        mat=pattern.matcher(ua);  
        if(mat.find()){  
            s=mat.group();  
            Sys.put("type", "safari");  
            Sys.put("version", s.split("/")[1].split(".")[0]);  
            return Sys;  
        }   
        return Sys;  
    }  	    

	/**
	 * 单个文件复制
	 * @param oldPath
	 * @param newPath
	 * @return
	 */
	public static boolean copyFile(String oldPath, String newPath) {
		boolean ok = true;
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			File newFile = new File(newPath);

			if (oldfile.exists()) {
				if(!newFile.exists()){
					newFile.mkdirs();
				}else{
					newFile.delete();
				}
				InputStream inStream = new FileInputStream(oldPath);
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1025*5];
				while ((byteread = inStream.read(buffer)) != -1) {
					//bytesum += byteread; // 字节数 文件大小
					//System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.close();
			}
		} catch (Exception e) {
			//System.out.println("复制单个文件操作出错 ");
			ok = false;

		}
		return ok;
	}
	
	/**
	 * 给不够长度的数字前方补零 
	 * @param destStrlen
	 * @param srcNumber
	 * @return
	 */
    public static String addZeroBeforeNumber(int destStrlen,String srcNumber){
        int srcStrlen = srcNumber.length();
        String destStr=srcNumber;
        for(int i=0;i<destStrlen-srcStrlen;i++){
            destStr="0"+destStr;
        }
        return destStr;
    }
	
    /**
     * Return time earlier than current time by given hours.
     * @param hours
     * @return
     */
    public static Date getUTCTimeHourBefore(int hours) {
        Calendar date = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        date.add(Calendar.HOUR, -hours);
        
        return date.getTime();
    }
 
    /**
     * Return time earlier than current time by given minutes.
     * @param hours
     * @return
     */
    public static Date getUTCTimeMinuteBefore(int minutes) {
        Calendar date = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        date.add(Calendar.MINUTE, -minutes);
        
        return date.getTime();
    }
    
    /**
     * Return UTC time
     * @return
     */
    public static Date getUTCTime() {
        Calendar date = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        
        return date.getTime();
    }
}
