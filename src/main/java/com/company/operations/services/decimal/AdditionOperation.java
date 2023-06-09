package com.company.operations.services.decimal;

import com.company.operations.enums.OperationType;
import com.company.operations.services.Operations;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class AdditionOperation implements Operations {

    public  String execute(List<String> strs) {
        String result = "0";

        for (String str : strs) {
            result = addTwoNumbers(result, str);
        }

        return result;
    }

    /**
     * A Method that adds two strings, where each string represents an
     * arbitrarily large non-negative number.
     * @param str1 string that represents the first number
     * @param str2 string that represents the second number
     * @return a string with the sum of str1 and str2
     */
    private static String addTwoNumbers(String str1, String str2) {
        if (str1.length() < str2.length()) {
            return addTwoNumbers(str2, str1);
        }

        str1 = new StringBuilder(str1).reverse().toString();
        str2 = new StringBuilder(str2).reverse().toString();

        StringBuilder result = new StringBuilder();
        int carry = 0;

        for (int i = 0; i < str1.length(); i++) {
            int digit1 = str1.charAt(i) - '0';
            int digit2 = i < str2.length() ? str2.charAt(i) - '0' : 0;

            int sum = digit1 + digit2 + carry;
            result.append(sum % 10);
            carry = sum / 10;
        }

        if (carry > 0) {
            result.append(carry);
        }

        return result.reverse().toString();
    }

    /**
     * @return An Operation Type used by OperationsFactory
     */
    @Override
    public OperationType getOperationType() {
        return OperationType.DECIMAL_ADDITION;
    }
}
