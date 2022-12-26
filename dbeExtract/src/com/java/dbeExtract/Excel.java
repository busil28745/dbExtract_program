package com.java.dbeExtract;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Set;
//import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	void excelFile(HashMap<String, ArrayList<ArrayList<String>>> dbMap, String name, String hostName, String DB_USER, String location) {
		XSSFWorkbook xworkbook = null;
		XSSFSheet xSheet = null;
		XSSFRow xRow = null;
		XSSFCell xCell = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		Date time = new Date();
		String currentTime = format.format(time);

		try {
			xworkbook = new XSSFWorkbook();
			//타이틀용 폰트
			XSSFFont title_Font = xworkbook.createFont();
			title_Font.setFontName("맑은 고딕");
			title_Font.setFontHeight(17.5);
			title_Font.setBold(true);

			XSSFFont font = xworkbook.createFont();
			font.setFontName("맑은 고딕");
			font.setFontHeight(10);

			//타이틀용 스타일
			CellStyle cell_Title = xworkbook.createCellStyle();
			cell_Title.setFont(title_Font);
			cell_Title.setWrapText(true);
			cell_Title.setAlignment(HorizontalAlignment.CENTER);
			cell_Title.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.index);
			cell_Title.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cell_Title.setBorderTop(BorderStyle.THIN);
			cell_Title.setBorderBottom(BorderStyle.THIN);
			cell_Title.setBorderRight(BorderStyle.THIN);
			cell_Title.setBorderLeft(BorderStyle.THIN);
			//어두운 회색(가운데 정렬)
			CellStyle dark_Grey = xworkbook.createCellStyle();
			dark_Grey.setFont(font);
			dark_Grey.setWrapText(true);
			dark_Grey.setAlignment(HorizontalAlignment.CENTER);
			dark_Grey.setVerticalAlignment(VerticalAlignment.CENTER);
			dark_Grey.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.index);
			dark_Grey.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			dark_Grey.setBorderTop(BorderStyle.THIN);
			dark_Grey.setBorderBottom(BorderStyle.THIN);
			dark_Grey.setBorderRight(BorderStyle.THIN);
			dark_Grey.setBorderLeft(BorderStyle.THIN);
			//밝은 회색
			CellStyle light_Grey = xworkbook.createCellStyle();
			light_Grey.setFont(font);
			light_Grey.setWrapText(true);
			light_Grey.setAlignment(HorizontalAlignment.CENTER);
			light_Grey.setVerticalAlignment(VerticalAlignment.CENTER);
			light_Grey.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
			light_Grey.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			light_Grey.setBorderTop(BorderStyle.THIN);
			light_Grey.setBorderBottom(BorderStyle.THIN);
			light_Grey.setBorderRight(BorderStyle.THIN);
			light_Grey.setBorderLeft(BorderStyle.THIN);
			//기본배경, 가운데 정렬
			CellStyle center = xworkbook.createCellStyle();
			center.setFont(font);
			center.setWrapText(true);
			center.setAlignment(HorizontalAlignment.CENTER);
			center.setVerticalAlignment(VerticalAlignment.CENTER);
			center.setBorderTop(BorderStyle.THIN);
			center.setBorderBottom(BorderStyle.THIN);
			center.setBorderRight(BorderStyle.THIN);
			center.setBorderLeft(BorderStyle.THIN);
			//기본배경, 정렬 없음
			CellStyle basic = xworkbook.createCellStyle();
			basic.setFont(font);
			basic.setWrapText(true);
			basic.setVerticalAlignment(VerticalAlignment.CENTER);
			basic.setBorderTop(BorderStyle.THIN);
			basic.setBorderBottom(BorderStyle.THIN);
			basic.setBorderRight(BorderStyle.THIN);
			basic.setBorderLeft(BorderStyle.THIN);

//			Object[] mapKey = dbMap.keySet().toArray();
//			Arrays.sort(mapKey);
//			System.out.println(Arrays.toString(mapKey));
			TreeMap<String, ArrayList<ArrayList<String>>> tm = new TreeMap<String, ArrayList<ArrayList<String>>>(dbMap);
			Iterator<String> keyiterator = tm.keySet().iterator();

			while (keyiterator.hasNext()) {
//			for (String key : dbMap.keySet()) {
				ArrayList<ArrayList<String>> valueList = new ArrayList<ArrayList<String>>();
				String key = (String) keyiterator.next();
				valueList = dbMap.get(key);
				int rowNo = 0;
				xSheet = xworkbook.createSheet(key); //sheet 생성
				//각 열 크기 설정
				xSheet.setColumnWidth(0, (xSheet.getColumnWidth(0) + (short) 2048));
				xSheet.setColumnWidth(1, (xSheet.getColumnWidth(1) + (short) 2048));
				xSheet.setColumnWidth(2, (xSheet.getColumnWidth(2) + (short) 4800));
				xSheet.setColumnWidth(3, (xSheet.getColumnWidth(3) + (short) 2048));
				xSheet.setColumnWidth(4, (xSheet.getColumnWidth(4) + (short) 2048));
				xSheet.setColumnWidth(5, (xSheet.getColumnWidth(5) + (short) 2800));
				xSheet.setColumnWidth(6, (xSheet.getColumnWidth(6) + (short) 2048));
				xSheet.setColumnWidth(7, (xSheet.getColumnWidth(7) + (short) 2048));
				xSheet.setColumnWidth(8, (xSheet.getColumnWidth(8) + (short) 2048));
				xSheet.setColumnWidth(9, (xSheet.getColumnWidth(9) + (short) 2048));
				xSheet.setColumnWidth(10, (xSheet.getColumnWidth(10) + (short) 2048));
				xSheet.setColumnWidth(11, (xSheet.getColumnWidth(11) + (short) 2048));
				xSheet.setColumnWidth(12, (xSheet.getColumnWidth(12) + (short) 4096));
				//셀 병합
				xSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));
				xSheet.addMergedRegion(new CellRangeAddress(1, 1, 7, 12));
				xSheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 3));
				xSheet.addMergedRegion(new CellRangeAddress(2, 2, 5, 12));
				xSheet.addMergedRegion(new CellRangeAddress(3, 3, 7, 12));
				xSheet.addMergedRegion(new CellRangeAddress(4, 4, 3, 12));
				xSheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 12));

				xRow = xSheet.createRow(rowNo++);

				xCell = xRow.createCell(0);
				xCell.setCellStyle(cell_Title);
				xCell.setCellValue("테이블 인덱스 정의서");

				xRow = xSheet.createRow(rowNo++);
				xCell = xRow.createCell(0); // A/2
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("HOST명");
				xCell = xRow.createCell(1); // B/2
				xCell.setCellStyle(basic);
				xCell.setCellValue(hostName);
				xCell = xRow.createCell(2); // C/2
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("DB USER");
				xCell = xRow.createCell(3); // D/2
				xCell.setCellStyle(basic);
				xCell.setCellValue(DB_USER);
				xCell = xRow.createCell(4); // E/2
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("작성일");
				xCell = xRow.createCell(5); // F/2
				xCell.setCellStyle(basic);
				xCell.setCellValue(currentTime);
				xCell = xRow.createCell(6); // G/2
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("작성자");
				xCell = xRow.createCell(7); // H/2
				xCell.setCellStyle(basic);
				xCell.setCellValue(name);
				xCell = xRow.createCell(8); // I/2
				xCell.setCellStyle(basic);
				xCell = xRow.createCell(9); // J/2
				xCell.setCellStyle(basic);
				xCell = xRow.createCell(10); // K/2
				xCell.setCellStyle(basic);
				xCell = xRow.createCell(11); // L/2
				xCell.setCellStyle(basic);
				xCell = xRow.createCell(12); // M/2
				xCell.setCellStyle(basic);

				xRow = xSheet.createRow(rowNo++);
				xRow.setHeight((short) 570);
				xCell = xRow.createCell(0); // A/3
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("TABLE 영문명" + "\n" + "(20자 이내)");
				xCell = xRow.createCell(1); // B/3
				xCell.setCellStyle(basic);
				xCell.setCellValue(key);
				xCell = xRow.createCell(2); // C/3
				xCell.setCellStyle(basic);
				xCell = xRow.createCell(3); // D/3
				xCell.setCellStyle(basic);
				xCell = xRow.createCell(4); // E/3
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("TABLE 한글명");
				xCell = xRow.createCell(5);
				xCell.setCellStyle(basic);

				xCell.setCellValue(((dbMap.get(key)).get(0)).get(0));
				for (int i = 6; i < 13; i++) { //F/3 ~ M/3
					xCell = xRow.createCell(i);
					xCell.setCellStyle(basic);
				}

				xRow = xSheet.createRow(rowNo++);
				xCell = xRow.createCell(0); // A/4
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("코드");
				xCell = xRow.createCell(1); // B/4
				xCell.setCellStyle(center);
				xCell.setCellValue("N");
				xCell = xRow.createCell(2); // C/4
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("마스터");
				xCell = xRow.createCell(3); // D/4
				xCell.setCellStyle(center);
				xCell.setCellValue("N");
				xCell = xRow.createCell(4); // E/4
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("로그");
				xCell = xRow.createCell(5); // F/4
				xCell.setCellStyle(center);
				xCell.setCellValue("N");
				xCell = xRow.createCell(6); // G/4
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("보관주기");
				xCell = xRow.createCell(7); // H/4
				xCell.setCellStyle(basic);
				xCell.setCellValue("영구");
				xCell = xRow.createCell(8); // I/4
				xCell.setCellStyle(basic);
				xCell = xRow.createCell(9); // J/4
				xCell.setCellStyle(basic);
				xCell = xRow.createCell(10); // K/4
				xCell.setCellStyle(basic);
				xCell = xRow.createCell(11); // L/4
				xCell.setCellStyle(basic);
				xCell = xRow.createCell(12); // M/4
				xCell.setCellStyle(basic);

				xRow = xSheet.createRow(rowNo++);
				xCell = xRow.createCell(0); // A/5
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("파티션 TABLE");
				xCell = xRow.createCell(1); // B/5
				xCell.setCellStyle(center);
				xCell.setCellValue("N");
				xCell = xRow.createCell(2); // C/5
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("파티션 기준");
				for (int i = 3; i < 13; i++) { // D/5 ~ M/5
					xCell = xRow.createCell(i);
					xCell.setCellStyle(center);
				}

				xRow = xSheet.createRow(rowNo++);
				xRow.setHeight((short) 570);
				xCell = xRow.createCell(0); // A/6
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("테이블 설명");
				xCell = xRow.createCell(1);
				xCell.setCellValue(((dbMap.get(key)).get(0)).get(0));
				xCell.setCellStyle(basic);
				for (int i = 2; i < 13; i++) { // B/6 ~ M/6
					xCell = xRow.createCell(i);
					xCell.setCellStyle(basic);
				}

				xRow = xSheet.createRow(rowNo++);
				xRow.setHeight((short) 570);
				xCell = xRow.createCell(0); // A/7 ~ M/7
				xCell.setCellStyle(dark_Grey);
				xCell.setCellValue("NO");
				xCell = xRow.createCell(1);
				xCell.setCellStyle(dark_Grey);
				xCell.setCellValue("필드명(영문)\n" + "(용어 통일성)");
				xCell = xRow.createCell(2);
				xCell.setCellStyle(dark_Grey);
				xCell.setCellValue("필드명(한글)\n" + "(용어 통일성)");
				xCell = xRow.createCell(3);
				xCell.setCellStyle(dark_Grey);
				xCell.setCellValue("TYPE\n" + "(타입 통일성)");
				xCell = xRow.createCell(4);
				xCell.setCellStyle(dark_Grey);
				xCell.setCellValue("길이\n" + "(길이 통일성)");
				xCell = xRow.createCell(5);
				xCell.setCellStyle(dark_Grey);
				xCell.setCellValue("NULL");
				xCell = xRow.createCell(6);
				xCell.setCellStyle(dark_Grey);
				xCell.setCellValue("PK");
				xCell = xRow.createCell(7);
				xCell.setCellStyle(dark_Grey);
				xCell.setCellValue("디폴트 값");
				xCell = xRow.createCell(8);
				xCell.setCellStyle(dark_Grey);
				xCell.setCellValue("제약사항");
				xCell = xRow.createCell(9);
				xCell.setCellStyle(dark_Grey);
				xCell.setCellValue("코드값(고정)");
				xCell = xRow.createCell(10);
				xCell.setCellStyle(dark_Grey);
				xCell.setCellValue("암호화대상\n" + "여부(Y/N)");
				xCell = xRow.createCell(11);
				xCell.setCellStyle(dark_Grey);
				xCell.setCellValue("암호화방식\n" + "(일방향/양방향)");
				xCell = xRow.createCell(12);
				xCell.setCellStyle(dark_Grey);
				xCell.setCellValue("암호화방식\n" + "(SHA-512/AES-256/ARIA)");

				//테이블 정보 입력
				for (ArrayList<String> infoList : valueList) {
					xRow = xSheet.createRow(rowNo++);
					for (int i = 0; i < infoList.size() - 1; i++) {
						xCell = xRow.createCell(i);
						xCell.setCellStyle(center);
						if (i == 0 || i == 4) {
							xCell.setCellValue(Integer.parseInt(infoList.get(i + 1)));
						} else {
							xCell.setCellValue(infoList.get(i + 1));
						}
					}
					for (int i = 8; i < 13; i++) { //제약사항 ~ 암호화 방식
						xCell = xRow.createCell(i);
						xCell.setCellStyle(center);
					}
				}
				//테이블 정보 빈칸 생성용(가시성 확보를 위해)
				for (int i = 0; i < 4; i++) {
					xRow = xSheet.createRow(rowNo++);
					for (int j = 0; j < 13; j++) {
						xCell = xRow.createCell(j);
						xCell.setCellStyle(center);
					}
				}
				xSheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 2, 12));
				xSheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo + 5, 0, 0));

				xRow = xSheet.createRow(rowNo++);
				xSheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 2, 12));
				xCell = xRow.createCell(0);
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("인덱스");
				xCell = xRow.createCell(1);
				xCell.setCellStyle(center);
				xCell.setCellValue("연관 trigger 명");
				xCell = xRow.createCell(2);
				xCell.setCellStyle(basic);
				xCell.setCellValue("없음");
				for (int i = 3; i < 13; i++) {
					xCell = xRow.createCell(i);
					xCell.setCellStyle(basic);
				}
				xRow = xSheet.createRow(rowNo++);
				xCell = xRow.createCell(0);
				xCell.setCellStyle(light_Grey);
				xCell = xRow.createCell(1);
				xCell.setCellStyle(center);
				xCell.setCellValue("연관 sequence명");
				xCell = xRow.createCell(2);
				xCell.setCellStyle(basic);
				xCell.setCellValue("SEQ_DEVICE_SEQ");
				for (int i = 3; i < 13; i++) {
					xCell = xRow.createCell(i);
					xCell.setCellStyle(basic);
				}
				xSheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo + 1, 1, 1));
				xSheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 3, 4));
				xSheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 6, 7));
				xSheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 8, 12));
				xRow = xSheet.createRow(rowNo++);
				xCell = xRow.createCell(0);
				xCell.setCellStyle(light_Grey);
				xCell = xRow.createCell(1);
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("NO");
				xCell = xRow.createCell(2);
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("인덱스명");
				xCell = xRow.createCell(3);
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("인덱스 칼럼");
				xCell = xRow.createCell(4);
				xCell.setCellStyle(light_Grey);
				xCell = xRow.createCell(5);
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("인덱스 종류");
				xCell = xRow.createCell(6);
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("주요 칼럼 분석");
				xCell = xRow.createCell(7);
				xCell.setCellStyle(light_Grey);
				xCell = xRow.createCell(8);
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("인덱스 선정기준");
				xCell = xRow.createCell(9);
				xCell.setCellStyle(light_Grey);
				xCell = xRow.createCell(10);
				xCell.setCellStyle(light_Grey);
				xCell = xRow.createCell(11);
				xCell.setCellStyle(light_Grey);
				xCell = xRow.createCell(12);
				xCell.setCellStyle(light_Grey);
				xSheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 3, 4));
				xSheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 8, 10));
				xSheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 11, 12));

				xRow = xSheet.createRow(rowNo++);
				xRow.setHeight((short) 825);
				xCell = xRow.createCell(0);
				xCell.setCellStyle(light_Grey);
				xCell = xRow.createCell(1);
				xCell.setCellStyle(light_Grey);
				xCell = xRow.createCell(2);
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("PK_ + 테이블명(PRIMARY KEY)\n" + "IDX_ + 테이블명 + _UK(UNIQUE)\n" + "IDX_ + 테이블명 + _01(일반)");
				xCell = xRow.createCell(3);
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("필드별 순서, 소팅 기준");
				xCell = xRow.createCell(4);
				xCell.setCellStyle(light_Grey);
				xCell = xRow.createCell(5);
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("글로벌 (PK, UK, NUK)\n" + "로컬 (PK, UK, NUK)");
				xCell = xRow.createCell(6);
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("컬럼명");
				xCell = xRow.createCell(7);
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("컬럼분포도\n" + "(좋음,보통,나쁨)");
				xCell = xRow.createCell(8);
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("Access path");
				xCell = xRow.createCell(9);
				xCell.setCellStyle(light_Grey);
				xCell = xRow.createCell(10);
				xCell.setCellStyle(light_Grey);
				xCell = xRow.createCell(11);
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("모든 경우의 index");
				xCell = xRow.createCell(12);
				xCell.setCellStyle(light_Grey);
				xSheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 3, 4));
				xSheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 8, 10));
				xSheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 11, 12));

				xRow = xSheet.createRow(rowNo++);
				xRow.setHeight((short) 825);
				xCell = xRow.createCell(0);
				xCell.setCellStyle(light_Grey);
				xCell = xRow.createCell(1);
				xCell.setCellStyle(center);
				xCell.setCellValue("1");
				xCell = xRow.createCell(2);
				xCell.setCellStyle(basic);
				xCell.setCellValue("PK_" + key);
				xCell = xRow.createCell(3);
				xCell.setCellStyle(basic);
				xCell = xRow.createCell(4);
				xCell.setCellStyle(basic);
				xCell = xRow.createCell(5);
				xCell.setCellStyle(basic);
				xCell.setCellValue("로컬 (PK)");
				xCell = xRow.createCell(6);
				xCell.setCellStyle(basic);
				String PK = "";
				for (ArrayList<String> infoList : valueList) {
					if ((infoList.get(7)).equals("PRI")) {
						PK = PK + (infoList.get(2)) + ", ";
					}
					xCell.setCellValue(PK);
				}

				xCell = xRow.createCell(7);
				xCell.setCellStyle(basic);
				xCell = xRow.createCell(8);
				xCell.setCellStyle(basic);
				xCell = xRow.createCell(9);
				xCell.setCellStyle(basic);
				xCell = xRow.createCell(10);
				xCell.setCellStyle(basic);
				xCell = xRow.createCell(11);
				xCell.setCellStyle(basic);
				xCell = xRow.createCell(12);
				xCell.setCellStyle(basic);
				xSheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 3, 4));
				xSheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 8, 10));
				xSheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 11, 12));

				xRow = xSheet.createRow(rowNo++);
				xCell = xRow.createCell(0);
				xCell.setCellStyle(light_Grey);
				xCell = xRow.createCell(1);
				xCell.setCellStyle(center);
				xCell.setCellValue("2");
				for (int i = 2; i < 13; i++) {
					xCell = xRow.createCell(i);
					xCell.setCellStyle(basic);
				}
				xSheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 1, 12));
				xRow = xSheet.createRow(rowNo++);
				xCell = xRow.createCell(0);
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("비고");
				for (int i = 1; i < 13; i++) {
					xCell = xRow.createCell(i);
					xCell.setCellStyle(basic);
				}
				xSheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 1, 12));

				xRow = xSheet.createRow(rowNo++);
				xRow.setHeight((short) 1815);
				xCell = xRow.createCell(0);
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("암호화여부\n" + "작성시\n" + "참고사항");
				xCell = xRow.createCell(1);
				xCell.setCellStyle(basic);
				xCell.setCellValue("\r\n"
						+ "  1) 암호화대상으로 사용되는 컬럼 정보로는 주민번호, 운전면허번호, 여권번호, 장애인번호, 외국인번호, 신용카드번호, ESN 번호, 비밀번호 등이 있습니다. 해당 데이터가 저장되는 컬럼은 반드시 암호화대상 여부에 표기를 해야 합니다.\r\n"
						+ "  2) 암호화방식은 양방향과 일방향 두가지 방식이 존재합니다. 양방향 방식은 비밀번호를 제외한 대부분의 개인정보 데이터가 대상으로 암호화된 데이터가 복호화되어 데이터를 확인할 수 있는 방식입니다. 이에 반해, 일방향 방식은 비밀번호와 같이 한번 암호화가 되면 복호화되지 않는 특징이 있습니다.  암호화대상은 반드시 이 둘 중 하나를 선택해야 합니다. \r\n"
						+ "  3) 유플러스 표준 암호화 알고리즘은 일방향 암호화 (SHA-512), 나머지는 양방향 암호화 (AES-256) 방식으로 적용합니다. 양방향 암호화 적용 시 Key 관리는 RSA2048로 설정해야 합니다.");
				for (int i = 2; i < 13; i++) {
					xCell = xRow.createCell(i);
					xCell.setCellStyle(basic);
				}
				xSheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 1, 12));

				xRow = xSheet.createRow(rowNo++);
				xRow.setHeight((short) 4140);
				xCell = xRow.createCell(0);
				xCell.setCellStyle(light_Grey);
				xCell.setCellValue("작성규칙");
				xCell = xRow.createCell(1);
				xCell.setCellStyle(basic);
				xCell.setCellValue("  1) 테이블 정의서 작성 주의 사항 : 기술한 내용과 DATABASE 의 SYSTEM CATALOG 정보가 일치해야 합니다.\r\n"
						+ "  2) 엑셀 시트명은 테이블(영문) 명으로 합니다.\r\n"
						+ "  3) 테이블 정의서는 테이블마다 각각의 탭으로 구분하여 작성합니다.\r\n"
						+ "\r\n"
						+ "-- 인덱스 참고\r\n"
						+ "1) primary key 또한 인덱스이므로 기입해 주셔야 합니다.\r\n"
						+ "2) 엑셀 시트명은 테이블 명으로 한다.\r\n"
						+ "3) 인덱스 선정 시 주의 사항 \r\n"
						+ "    - access path를 기준으로 인덱스를 생성해 주셔야 합니다. \r\n"
						+ "    - 중복된 인덱스 및 단일 컬럼 인덱스는 가급적 지양해 주시길 부탁합니다.\r\n"
						+ "    - 분포도가 3 ~ 5 % 미만인 컬럼을 인덱스로 선정해야 인덱스 선정에 의한 효과를 기대할 수 있습니다.\r\n"
						+ "    - 분포도 기준 좋음 1%미만 보통 5%미만 나쁨 5%이상\r\n"
						+ "4) 인덱스 명칭은 아래와 같은 설정으로 생성해야 합니다.\r\n"
						+ "    PK_ + 테이블명(PRIMARY KEY)\r\n"
						+ "    IDX_ + 테이블명 + _UK(UNIQUE)\r\n"
						+ "    IDX_ + 테이블명 + _01(일반)\r\n"
						+ "5) 인덱스 종류\r\n"
						+ "    PK ( PRIMARY KEY)\r\n"
						+ "    UK ( UNIQUE KEY)\r\n"
						+ "    NK (NON_UNIQUE KEY)");
				for (int i = 2; i < 13; i++) {
					xCell = xRow.createCell(i);
					xCell.setCellStyle(basic);
				}
			}

			String localFile = location + "\\" + "YYYYMMDD_서비스명_AND-12_테이블인덱스정의서DB명_v1.0" + ".xlsx";

			File file = new File(localFile);
			FileOutputStream fos = null;
			fos = new FileOutputStream(file);
			xworkbook.write(fos);

			if (fos != null)
				fos.close();

		} catch (Exception e) {

		} finally {
			System.out.println("끝");
		}
	}
}
