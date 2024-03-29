package com.jian.website.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.jian.annotation.PrimaryKey;
import com.jian.annotation.PrimaryKeyCondition;
import com.jian.tools.core.DateTools;
import com.jian.tools.core.Tools;

public class Utils {

	private static Map<String, List<PrimaryKeyCondition>> pkArrays = new HashMap<String ,List<PrimaryKeyCondition>>();
	
	/**
	 * 获取Entity类注解的PrimaryKey。
	 * @param clss
	 * @return
	 */
	public static List<String> getPrimaryKeys(Class<?> clss){
		String mark = clss.getCanonicalName();
		List<PrimaryKeyCondition> list = null;
		if(pkArrays.containsKey(mark)){
			list = pkArrays.get(mark);
		}else {
			list = new ArrayList<PrimaryKeyCondition>();
			Field[] fields = Tools.getFields(clss);
			for (Field f : fields) {
				if(f.isAnnotationPresent(PrimaryKey.class)){
					PrimaryKeyCondition node = new PrimaryKeyCondition();
					node.setField(f.getName());
					node.setType(f.getType().getSimpleName());
					node.setKeyType(f.getAnnotation(PrimaryKey.class).type());
					list.add(node);
				}
			}
			pkArrays.put(mark, list);
		}
		List<String> keys = new ArrayList<>();
		for (PrimaryKeyCondition pkc : list) {
			String pkn = pkc.getField();
			keys.add(pkn);
		}
		return keys;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getObejctClass(Class<?> clzz){
		Type type = clzz.getGenericSuperclass();
		Class<T> clss = null;
		try {
			Class<?>[] clsses = Tools.getGenericClass((ParameterizedType) type);
			clss = (Class<T>) clsses[0];
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return clss;
	}

	public static String newId() {
		int random = new Random().nextInt(10000);
		String str = random+"";
		for (int i = 0; i <  4 - str.length(); i++) {
			str = "0" + str;
		}
		return ""+DateTools.formatDate("yyyyMMddHHmmssSSS")+str;
		//return Tools.md5(UUID.randomUUID().toString());
	}
	
	public static byte[] compress(byte[] buff) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		GZIPOutputStream gzos = new GZIPOutputStream(baos);
		gzos.write(buff);
		gzos.close();
		baos.close();
		return baos.toByteArray();
	}
	
	public static byte[] uncompress(byte[] buff) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream bais = new ByteArrayInputStream(buff);
		GZIPInputStream gzis = new GZIPInputStream(bais);
		byte[] temp = new byte[1024];
		int len = 0;
		while ((len = gzis.read(temp)) >= 0) {
			out.write(temp, 0, len);
		}
		byte[] receive = out.toByteArray();
		gzis.close();
		bais.close();
		return receive;
	}
	
	public static void main(String[] args) throws IOException {
		byte[] b  = new byte[1000];
		for (int i = 0;i < b.length;++i) {
			b[i] = 65;
		}
		byte[] buff = compress(b);
		System.out.println(buff.length);
		
		byte[] c = uncompress(buff);
		System.out.println(c.length);
	}
}
