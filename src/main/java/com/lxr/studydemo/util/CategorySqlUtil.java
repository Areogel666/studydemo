package com.lxr.studydemo.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.lxr.studydemo.model.IntlCategory;

import java.util.List;

/**
 * 解析json，拼接sql添加目录多国语言
 */
public class CategorySqlUtil {

    public static void main(String[] args) {
        printSql();
    }

    private static void printSql() {
        Gson gson = new GsonBuilder().create();
        List<LinkedTreeMap> list = gson.fromJson(JSON_STR, List.class);

        for (int i = 0; i < list.size(); i++) {
            LinkedTreeMap map = list.get(i);
            int categoryid = ((Double)map.get("categoryid")).intValue();
            IntlCategory.CategoryName categoryname = gson.fromJson(gson.toJson(map.get("categoryname")), IntlCategory.CategoryName.class);
            String sql = UPDATE_SQL_1 + categoryname.getZh_CN() + UPDATE_SQL_2 + categoryid + SEMICOLON;
            System.out.println(sql);
        }
    }

    private static String UPDATE_SQL_1 = "update intl_category set category_name = json_insert(category_name,\"$.vi_VN\",\"";
    private static String UPDATE_SQL_2 = "\") where category_id = ";
    private static String SEMICOLON = ";";

    private static String JSON_STR = "[\n" +
            "    {\n" +
            "        \"categoryid\": 1,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Android Wear\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 2,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Nghệ thuật & Thiết kế\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 3,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Ô tô & Xe cộ\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 4,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Làm đẹp\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 5,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Sách\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 6,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Kinh doanh\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 7,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Hoạt hình\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 8,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Truyền thông\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 9,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Hẹn hò\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 10,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Giáo dục\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 11,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Giải trí\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 12,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Sự kiện\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 13,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Tài chính\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 14,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Thức ăn & Đồ uống\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 15,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Sức khỏe & Tập luyện\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 16,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Nhà cửa & Gia đình\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 17,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Thư viện & Demo\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 18,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Phong cách sống\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 19,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Bản đồ & Điều hướng\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 20,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Y tế\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 21,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Nhạc & Âm thanh\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 22,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Tin tức & Tạp chí\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 23,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Nuôi dạy con\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 24,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Cá nhân\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 25,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Nhiếp ảnh\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 26,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Năng suất\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 27,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Mua sắm\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 28,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Xã hội\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 29,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Thể thao\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 30,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Công cụ\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 31,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Địa phương & Du lịch\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 32,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Trình phát & Biên tập viên\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 33,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Thời tiết\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 34,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Hình nền\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 35,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Phương tiện & Video\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 36,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Giao thông\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 37,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Tiện ích\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 101,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Hành động\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 102,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Phiêu lưu\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 103,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Game thùng\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 104,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Trò chơi cờ bàn\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 105,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Thẻ\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 106,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Casino\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 107,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Thông thường\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 108,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Thuộc giáo dục\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 109,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Nhạc\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 110,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Giải đố\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 111,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Đua xe\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 112,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Nhập vai\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 113,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Mô phỏng\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 114,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Thể thao\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 115,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Chiến lược\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 116,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Giải đố\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 117,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Word\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 118,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Gia đình\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 119,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Trò chơi\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 120,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Hành động & Phiêu lưu\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 121,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Rèn luyện trí não\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 122,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Sáng tạo\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 123,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Giáo dục\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 124,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Nhạc & Video\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"categoryid\": 125,\n" +
            "        \"categoryname\": {\n" +
            "            \"zh_CN\": \"Thời trang\"\n" +
            "        }\n" +
            "    }\n" +
            "]\n";
}
