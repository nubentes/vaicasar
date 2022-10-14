import React from 'react';
import { ScrollView } from 'react-native';
import { useTheme } from 'styled-components';
import Header from '../../components/Header';
import List from '../../components/List';

import { Container } from './styles';

export default function Home() {
  const theme = useTheme();

  return (
    <Container>
      <ScrollView showsVerticalScrollIndicator={false}>
        <Header banner="223 dias" color={theme.colors.green} />

        <List />
      </ScrollView>
    </Container>
  );
}
