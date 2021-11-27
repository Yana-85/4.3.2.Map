package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FileOpenManagerTest {
    private FileOpenManager manager = new FileOpenManager();

    private String app1 = "Google Chrome";
    private String app2 = "Microsoft Word";
    private String app3 = "Adobe Photoshop";
    private String app4 = "IntelliJ IDEA";
    private String keyWithUpperCase = "JPEGAPP";
    private String extension1 = ".html";
    private String extension2 = ".doc";
    private String extension3 = ".psd";
    private String extension4 = ".java";
    private String extension5 = ".pdf";

    @Nested
    public class EmptyManager {

        @Test
        public void shouldReturnNullIfNoApp() {
            String expected = null;
            String actual = manager.getApp(extension1);
            assertEquals(expected, actual);
        }

        @Test
        public void shouldReturnEmptyIfNothingToRemove() {
            Map<String, String> expected = new HashMap<>();
            manager.removeKey(extension1);
            HashMap<String, String> actual = manager.getMaps();
            assertEquals(expected, actual);
        }

        @Test
        public void shouldGetEmptyIfNoKeys() {
            List<String> expected = List.of();
            List<String> actual = manager.getAllKeys();
            assertEquals(expected, actual);
        }

        @Test
        public void shouldGetEmptyIfNoValues() {
            List<String> expected = List.of();
            List<String> actual = manager.getAllValues();
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class SingleItemManager {

        @BeforeEach
        public void setUp() {
            manager = new FileOpenManager();
            manager.registerApp(extension1, app1);
        }

        @Test
        public void shouldReturnApp() {
            String expected = app1;
            String actual = manager.getApp(extension1);
            assertEquals(expected, actual);
        }

        @Test
        public void shouldRemoveKey() {
            Map<String, String> expected = new HashMap<>();
            manager.removeKey(extension1);
            HashMap<String, String> actual = manager.getMaps();
            assertEquals(expected, actual);
        }

        @Test
        public void shouldGetOneKey() {
            List<String> expected = List.of(extension1);
            List<String> actual = manager.getAllKeys();
            assertEquals(expected, actual);
        }

        @Test
        public void shouldGetOneValue() {
            List<String> expected = List.of(app1);
            List<String> actual = manager.getAllValues();
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class MultipleItemsManager {

        @BeforeEach
        public void setUp() {
            manager = new FileOpenManager();
            manager.registerApp(extension1, app1);
            manager.registerApp(extension2, app2);
            manager.registerApp(extension3, app3);
            manager.registerApp(extension4, app4);
        }

        @Test
        public void shouldAddAll() {
            HashMap<String, String> expected = new HashMap<>();
            expected.put(extension1, app1);
            expected.put(extension2, app2);
            expected.put(extension3, app3);
            expected.put(extension4, app4);
            HashMap<String, String> actual = manager.getMaps();
            assertEquals(expected, actual);
        }

        @Test
        public void shouldReturnOneApp() {
            String expected = app1;
            String actual = manager.getApp(extension1);
            assertEquals(expected, actual);
        }

        @Test
        public void shouldReturnNullIfInvalidKey() {
            String expected = null;
            String actual = manager.getApp(extension5);
            assertEquals(expected, actual);
        }

        @Test
        public void shouldReturnNullIfKeyWithUpperCase() {
            String expected = null;
            String actual = manager.getApp(keyWithUpperCase);
            assertEquals(expected, actual);
        }

        @Test
        public void shouldRemoveKey() {
            manager.removeKey(extension2);
            Map<String, String> expected = new HashMap<>();
            expected.put(extension1, app1);
            expected.put(extension3, app3);
            expected.put(extension4, app4);
            HashMap<String, String> actual = manager.getMaps();
            assertEquals(expected, actual);
        }

        @Test
        public void shouldNotRemoveKeyIfUpperCase() {
            manager.removeKey(keyWithUpperCase);
            Map<String, String> expected = new HashMap<>();
            expected.put(extension1, app1);
            expected.put(extension2, app2);
            expected.put(extension3, app3);
            expected.put(extension4, app4);
            HashMap<String, String> actual = manager.getMaps();
            assertEquals(expected, actual);
        }

        @Test
        public void shouldNotRemoveKeyIfInvalid() {
            manager.removeKey(extension5);
            Map<String, String> expected = new HashMap<>();
            expected.put(extension1, app1);
            expected.put(extension2, app2);
            expected.put(extension3, app3);
            expected.put(extension4, app4);
            HashMap<String, String> actual = manager.getMaps();
            assertEquals(expected, actual);
        }

        @Test
        public void shouldGetAllKeysSorted() {
            List<String> expected = List.of(extension2, extension1, extension4, extension3);
            List<String> actual = manager.getAllKeys();
            assertEquals(expected, actual);
        }

        @Test
        public void shouldGetAllValuesSorted() {
            List<String> expected = List.of(app3, app1, app4, app2);
            List<String> actual = manager.getAllValues();
            assertEquals(expected, actual);
        }
    }
}