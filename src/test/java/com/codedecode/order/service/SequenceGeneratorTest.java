package com.codedecode.order.service;

import com.codedecode.order.dto.Sequence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SequenceGeneratorTest {

    @Mock
    private MongoOperations mongoOperations;

    @InjectMocks
    private SequenceGenerator sequenceGenerator;

    @Test
    void testGenerateNextOrderId() {
        // Arrange
        Sequence sequence = new Sequence();
        sequence.setSequenceKey(100);

        when(mongoOperations.findAndModify(
                ArgumentMatchers.<Query>any(),
                ArgumentMatchers.<Update>any(),
                ArgumentMatchers.<FindAndModifyOptions>any(),
                ArgumentMatchers.eq(Sequence.class)))
                .thenReturn(sequence);

        // Act
        int result = sequenceGenerator.generateNextOrderId();

        // Assert
        assertEquals(100, result);
    }
}
