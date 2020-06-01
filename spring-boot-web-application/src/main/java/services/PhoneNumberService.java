package services;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


import controllers.response.PhoneNumberCombinationVO;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Service
public class PhoneNumberService {
	
	public PhoneNumberCombinationVO getPhoneNumber(int pageNo, int pageSize, String number){

		PhoneNumberCombinationVO vo = new PhoneNumberCombinationVO();
		HashMap<Integer, Character[]> keypad = new HashMap<Integer, Character[]>();
		keypad.put(0, new Character[]{'0'});
		keypad.put(1, new Character[]{'1'});
		keypad.put(2, new Character[]{'a','b', 'c'});
        keypad.put(3, new Character[]{'d','e', 'f'});
        keypad.put(4, new Character[]{'g','h', 'i'});
        keypad.put(5, new Character[]{'j','k', 'l'});
        keypad.put(6, new Character[]{'m','n', 'o'});
        keypad.put(7, new Character[]{'p','q', 'r', 's'});
        keypad.put(8, new Character[]{'t','u', 'v'});
        keypad.put(9, new Character[]{'w','x', 'y', 'z'});

        List<String> resultList = new LinkedList<String>();
        String  temp = "";
		PhoneNumberCombination(0,keypad,resultList,number, temp);
		long totalOfComb = resultList.size();
		long TotalPage = totalOfComb/pageSize;

		List<List<String>> pageList = subList(resultList, pageSize);

		// set vo
		vo.setData(pageList.get(pageNo));
		vo.setNumber(number);
		vo.setPage(pageNo);
		vo.setRowPerPage(pageSize);
		vo.setTotalPage(TotalPage + 1);
		vo.setTotalRecords(totalOfComb);

		return vo;
	
	}

	public static void PhoneNumberCombination(int startpos, Map keypadmap, List resultList, String inputNumber,
			String resultTemp) {
		if (startpos > inputNumber.length() - 1) {
			return;
		}

		// recursive, find the corresponding letter
		int curPos = Character.getNumericValue(inputNumber.charAt(startpos));

		for (Character ch : (Character[]) keypadmap.get(curPos)) {
			PhoneNumberCombination(startpos + 1, keypadmap, resultList, inputNumber, resultTemp + ch);
			if (startpos == inputNumber.length() - 1) {
				resultList.add(resultTemp + ch);
			}

		}
	}

	private static List<List<String>> subList(List<String> list, int pageSize) {

		int length = list.size();
		if (length < pageSize) {
			List<List<String>> newList = new ArrayList<>();
			newList.add(list);
			return newList;
		}

		// split the list
		int num = (length + pageSize - 1) / pageSize;
		List<List<String>> newList = new ArrayList<>(num);
		for (int i = 0; i < num; i++) {
			int startpos = i * pageSize;
			int endpos = i * pageSize;
			if ((i + 1) * pageSize < length) {
				endpos = (i + 1) * pageSize;
			} else {
				endpos = length;
			}
			newList.add(list.subList(startpos, endpos));
		}
		return newList;
	}
}