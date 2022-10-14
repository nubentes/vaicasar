import React from 'react';
import { ThemeProvider } from 'styled-components';
import TaskProvider from './src/context/list';
import Routes from './src/routes/app.routes';
import theme from './src/styles/theme';

export default function App() {
  return (
    <ThemeProvider theme={theme}>
      <TaskProvider>
        <Routes />
      </TaskProvider>
    </ThemeProvider>
  );
}
