package test;

import org.junit.jupiter.api.Test;
import sourcecode.StringPermutations;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class StringPermutationsTest {

	@Test
	void testPermutationsWithDuplicates() {
	    String input = "aab";
	    List<String> permutations = StringPermutations.generatePermutations(input, false);
	    
	    // Verify: Check the size of the list (should be 3 for "aab")
	    assertEquals(3, permutations.size());

	    // Check if all expected permutations are included
	    assertTrue(permutations.contains("aab"));
	    assertTrue(permutations.contains("aba"));
	    assertTrue(permutations.contains("baa"));
	}

    @Test
    void testPermutationsWithoutDuplicates() {
        String input = "abc";
        List<String> permutations = StringPermutations.generatePermutations(input, true);
        
        // Verify: Check the size of the list (should be 6 for "abc")
        assertEquals(6, permutations.size());
        assertTrue(permutations.contains("abc"));
        assertTrue(permutations.contains("acb"));
        assertTrue(permutations.contains("bac"));
        assertTrue(permutations.contains("bca"));
        assertTrue(permutations.contains("cab"));
        assertTrue(permutations.contains("cba"));
    }

    @Test
    void testEmptyString() {
        List<String> permutations = StringPermutations.generatePermutations("", true);
        
        // Verify: Should only have the empty string
        assertEquals(1, permutations.size());
        assertTrue(permutations.contains(""));
    }
}
