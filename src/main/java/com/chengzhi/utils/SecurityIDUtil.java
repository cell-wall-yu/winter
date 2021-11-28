package com.chengzhi.utils;

import java.util.ArrayList;
import java.util.List;

public class SecurityIDUtil {
    public static String KEY = "code-generate102";

    public static void updateKey(String key) {
        KEY = key;
    }

    /**
     * 加密一个Id
     *
     * @param id
     * @return
     */
    public static String encryptId(Long id) {
        if (id == null) {
            return null;
        }
        return EncryptUtil.aesEncode(String.valueOf(id), KEY);
    }

    /**
     * 加密一个Id
     *
     * @param id
     * @return
     */
    public static String encryptStrId(String id) {
        return EncryptUtil.aesEncode(id, KEY);
    }

    /**
     * 解密一个Id
     *
     * @param sid
     * @return
     */
    public static long decryptId(String sid) {
        String s = EncryptUtil.aesDecode(sid, KEY);
        return Long.valueOf(s);
    }

    /**
     * 解密一个Id
     *
     * @param sid
     * @return
     */
    public static String decryptIdToString(String sid) {
        return EncryptUtil.aesDecode(sid, KEY);
    }


    /**
     * 解决数组 格式：["加密串1", "加密串2", ...]
     *
     * @param jsonArrayInput
     * @return
     */
    public static String decryptJsonArray(String jsonArrayInput) {

        List<String> list = JsonUtil.fromJson(jsonArrayInput, List.class);

        List<String> result = new ArrayList<String>();
        if (list != null && list.size() > 0) {
            for (String string : list) {
                result.add(decryptIdToString(string));
            }
        }

        return JsonUtil.toString(result);
    }

    /**
     * 解决数组 格式：["加密串1", "加密串2", ...]
     *
     * @param jsonArrayInput
     * @return
     */
    public static List<Long> decryptJsonArrayToLong(String jsonArrayInput) {

        List<String> list = JsonUtil.fromJson(jsonArrayInput, List.class);

        List<Long> result = new ArrayList<Long>();
        if (list != null && list.size() > 0) {
            for (String string : list) {
                result.add(decryptId(string));
            }
        }

        return result;
    }

    /**
     * 加密一个json字符串
     *
     * @param jsonArrayInput
     * @return
     */
    public static String encryptJsonArray(String jsonArrayInput) {

        List<String> result = new ArrayList<String>();

        List<String> list = JsonUtil.fromJson(jsonArrayInput, List.class);
        if (list != null && list.size() > 0) {
            for (String string : list) {
                result.add(encryptStrId(string));
            }
        }

        return JsonUtil.toString(result);
    }

    /**
     * 解密一个Id
     *
     * @param sid
     * @return
     */
    public static String decryptStrId(String sid) {
        return EncryptUtil.aesDecode(sid, KEY);
    }

    /**
     * 批量解密
     *
     * @param sids  加密的id集合
     * @param split 分隔符
     * @return
     */
    public static List<Long> decryptIds(String sids, String split) {
        if (sids == null || sids.matches("\\s*") || split == null) {
            return null;
        }

        String[] tmps = sids.split(split);
        List<Long> result = new ArrayList<Long>(tmps.length);
        for (String string : tmps) {
            result.add(decryptId(string));
        }
        return result;
    }

    /**
     * 加密一个Id
     *
     * @param id
     * @return
     */
    public static String encryptId(long id) {
        String encode = EncryptUtil.aesEncode(String.valueOf(id), KEY);
        return encode;
    }

    /**
     * 加密一个Id
     *
     * @param ids
     * @return
     */
    public static List<String> encryptIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return null;
        }
        List<String> idList = new ArrayList<String>(ids.size());
        for (Long id : ids) {
            idList.add(EncryptUtil.aesEncode(String.valueOf(id), KEY));
        }
        return idList;
    }

    /**
     * 批量解密
     *
     * @return
     */
    public static List<Long> decryptIds(List<String> securityIds) {

        if (securityIds == null || securityIds.isEmpty()) {
            return null;
        }

        List<Long> result = new ArrayList<Long>(securityIds.size());
        for (String string : securityIds) {
            result.add(decryptId(string));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(encryptId(1));
        System.out.println(decryptId("2aa35df4308d6147ed0abf583d02376c"));
    }
}