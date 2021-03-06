package com.qileyuan.tatala.socket.to.helper;

import java.util.Iterator;
import java.util.Map;

import com.qileyuan.tatala.socket.io.TransferOutputStream;
import com.qileyuan.tatala.socket.to.TransferObject;
import com.qileyuan.tatala.socket.util.TransferUtil;

public class ByteArrayMapHelper {
	
	public static int getByteArrayLength(Map<String, byte[]> byteArrayMap){
		int blength = 0;
		Iterator<String> keyIt = byteArrayMap.keySet().iterator();
		while (keyIt.hasNext()) {
			// type
			blength += TransferUtil.getLengthOfByte();
			// key
			String key = (String) keyIt.next();
			blength += TransferUtil.getLengthOfString(key);
			// value
			byte[] values = byteArrayMap.get(key);
			blength += TransferUtil.getLengthOfByteArray(values);
		}
		return blength;
	}
	
	public static void getByteArray(Map<String, byte[]> byteArrayMap, TransferOutputStream touts) {
		Iterator<String> keyIt = byteArrayMap.keySet().iterator();
		while (keyIt.hasNext()) {
			// type
			touts.writeByte(TransferObject.DATATYPE_BYTEARRAY);
			// key
			String key = (String) keyIt.next();
			touts.writeString(key);
			// value
			byte[] values = byteArrayMap.get(key);
			touts.writeByteArray(values);
		}
	}
}
