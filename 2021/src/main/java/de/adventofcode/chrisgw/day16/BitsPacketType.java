package de.adventofcode.chrisgw.day16;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.function.BinaryOperator;

public enum BitsPacketType {
    SUM(0, BigInteger::add),
    PRODUCT(1, BigInteger::multiply),
    MINIMUM(2, BigInteger::min),
    MAXIMUM(3, BigInteger::max),
    LITERAL_VALUE(4, (bitInteger, otherBigInteger2) -> bitInteger),
    GREATER_THAN(5, (bigInteger, otherBigInteger2) -> {
        if (bigInteger.compareTo(otherBigInteger2) > 0) return BigInteger.ONE;
        return BigInteger.ZERO;
    }),
    LESS_THAN(6, (bigInteger, otherBigInteger2) -> {
        if (bigInteger.compareTo(otherBigInteger2) < 0) return BigInteger.ONE;
        return BigInteger.ZERO;
    }),
    EQUAL_TO(7, (bigInteger, otherBigInteger2) -> {
        if (bigInteger.equals(otherBigInteger2)) return BigInteger.ONE;
        return BigInteger.ZERO;
    });

    private final int typeId;
    private final BinaryOperator<BigInteger> reduceOperation;

    BitsPacketType(int typeId, BinaryOperator<BigInteger> reduceOperation) {
        this.typeId = typeId;
        this.reduceOperation = reduceOperation;
    }

    public static BitsPacketType valueOf(int typeId) {
        return Arrays.stream(BitsPacketType.values())
                .filter(bitsPacketType -> bitsPacketType.getTypeId() == typeId)
                .findAny()
                .orElseThrow();
    }


    public BinaryOperator<BigInteger> getReduceOperation() {
        return reduceOperation;
    }

    public int getTypeId() {
        return typeId;
    }

}
