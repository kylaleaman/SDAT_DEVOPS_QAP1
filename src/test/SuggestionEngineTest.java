package com.keyin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class SuggestionEngineTest {

    private SuggestionEngine suggestionEngine;

    @BeforeEach
    public void setUp() {
        suggestionEngine = new SuggestionEngine();
    }

    @Test
    public void testGenerateSuggestionsForMisspelledWord() throws IOException {
        // Load dictionary data
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));

        // Test with a misspelled word
        String suggestions = suggestionEngine.generateSuggestions("hellw");

        // Assert that the suggestion contains the correct word "hello"
        Assertions.assertTrue(suggestions.contains("hello"));
    }

    @Test
    public void testGenerateSuggestionsForCorrectWord() throws IOException {
        // Load dictionary data
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));

        // Test with a correctly spelled word
        String suggestions = suggestionEngine.generateSuggestions("hello");

        // Assert that no suggestions are returned for the correctly spelled word
        Assertions.assertTrue(suggestions.isEmpty());
    }

    @Test
    public void testGenerateSuggestionsWithMockData() {
        // Create a mock SuggestionsDatabase
        SuggestionsDatabase mockSuggestionDB = new SuggestionsDatabase();
        mockSuggestionDB.getWordMap().put("test", 1);

        // Set the mock SuggestionsDatabase to the SuggestionEngine
        suggestionEngine.setWordSuggestionDB(mockSuggestionDB);

        // Test with a word that should have a suggestion in the mock data
        String suggestions = suggestionEngine.generateSuggestions("tes");

        // Assert that the suggestion contains the expected word "test"
        Assertions.assertTrue(suggestions.contains("test"));
    }
}
