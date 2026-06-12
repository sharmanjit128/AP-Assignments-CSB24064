import { useState } from 'react';
import { StyleSheet, Text, TouchableOpacity, View } from 'react-native';

export default function HomeScreen() {
  const [count, setCount] = useState(0);
  const [isDarkMode, setIsDarkMode] = useState(false);

  const handleIncrement = () => setCount((prev) => prev + 1);
  const handleDecrement = () => setCount((prev) => (prev > 0 ? prev - 1 : prev));
  const handleReset = () => setCount(0);
  const toggleTheme = () => setIsDarkMode((prev) => !prev);

  const backgroundColor = isDarkMode ? '#121212' : '#FFFFFF';
  const textColor = isDarkMode ? '#F3F4F6' : '#111827';
  const buttonBackground = isDarkMode ? '#27272a' : '#E5E7EB';
  const buttonTextColor = isDarkMode ? '#FFFFFF' : '#111827';

  return (
    <View style={[styles.container, { backgroundColor }]}> 
      <Text style={[styles.title, { color: textColor }]}>Digital Counter</Text>

      <View style={styles.counterCard}>
        <Text style={[styles.counterValue, { color: textColor }]}>{count}</Text>
      </View>

      <View style={styles.buttonRow}>
        <TouchableOpacity style={[styles.button, styles.buttonSpacing, { backgroundColor: buttonBackground }]} onPress={handleDecrement}>
          <Text style={[styles.buttonText, { color: buttonTextColor }]}>- Decrement</Text>
        </TouchableOpacity>
        <TouchableOpacity style={[styles.button, { backgroundColor: buttonBackground }]} onPress={handleIncrement}>
          <Text style={[styles.buttonText, { color: buttonTextColor }]}>+ Increment</Text>
        </TouchableOpacity>
      </View>

      <TouchableOpacity style={[styles.button, styles.resetButton, { backgroundColor: buttonBackground }]} onPress={handleReset}>
        <Text style={[styles.buttonText, { color: buttonTextColor }]}>Reset</Text>
      </TouchableOpacity>

      <TouchableOpacity style={[styles.button, styles.themeButton, { backgroundColor: buttonBackground }]} onPress={toggleTheme}>
        <Text style={[styles.buttonText, { color: buttonTextColor }]}>Toggle Theme</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 24,
  },
  title: {
    fontSize: 28,
    fontWeight: '700',
    marginBottom: 24,
  },
  counterCard: {
    width: 180,
    height: 180,
    borderRadius: 18,
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: 24,
    shadowColor: '#000',
    shadowOpacity: 0.08,
    shadowRadius: 10,
    shadowOffset: { width: 0, height: 8 },
    elevation: 5,
  },
  counterValue: {
    fontSize: 72,
    fontWeight: '800',
  },
  buttonRow: {
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: 16,
  },
  buttonSpacing: {
    marginRight: 12,
  },
  button: {
    paddingVertical: 14,
    paddingHorizontal: 18,
    borderRadius: 14,
    minWidth: 140,
    alignItems: 'center',
  },
  resetButton: {
    marginBottom: 16,
  },
  themeButton: {
    minWidth: 280,
  },
  buttonText: {
    fontSize: 16,
    fontWeight: '600',
  },
});
