package com.company.operations.services.math;

import com.company.operations.enums.OperationType;
import com.company.operations.services.Operations;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class AdditionOperation implements Operations {

    public String execute(List<String> strs) {
        int maxLength = findMaxLength(strs);
        StringBuilder result = new StringBuilder(maxLength + 1);
        int carry = 0;

        for (int i = 0; i < maxLength; i++) {
            int sum = carry;
            for (String str : strs) {
                int digit = getDigit(str, i);
                sum += digit;
            }

            carry = sum / 10;
            int digit = sum % 10;
            result.append(digit);
        }

        if (carry != 0) {
            result.append(carry);
        }

        return result.reverse().toString();
    }

    private static int findMaxLength(List<String> strs) {
        int maxLength = 0;
        for (String str : strs) {
            maxLength = Math.max(maxLength, str.length());
        }
        return maxLength;
    }

    private static int getDigit(String str, int index) {
        if (index >= 0 && index < str.length()) {
            return Character.getNumericValue(str.charAt(index));
        }
        return 0;
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.ADDITION;
    }
}
