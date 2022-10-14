import React from 'react';
import { ScrollView } from 'react-native';
import Header from '../../components/Header';
import List, { Task } from '../../components/List';
import { Container } from './styles';

export default function Home() {
  const arr: Task[] = [
    {
      icon: 'silverware-fork-knife',
      title: 'Buffet',
      date: '21/08/2022',
      finished: true,
    },
    {
      icon: 'book',
      title: 'Cerimônia',
      date: '22/08/2022',
      finished: false,
    },
    {
      icon: 'party-popper',
      title: 'Festa',
      date: '23/08/2022',
      finished: false,
    },
    {
      icon: 'map-marker-outline',
      title: 'Lua de Mel',
      date: '23/08/2022',
      finished: false,
    },
    {
      icon: 'music',
      title: 'Música',
      date: '23/08/2022',
      finished: false,
    },
    {
      icon: 'music',
      title: 'Música1',
      date: '23/08/2022',
      finished: false,
    },
    {
      icon: 'music',
      title: 'Música2',
      date: '23/08/2022',
      finished: false,
    },
    {
      icon: 'music',
      title: 'Música3',
      date: '23/08/2022',
      finished: false,
    },
    {
      icon: 'music',
      title: 'Música4',
      date: '23/08/2022',
      finished: false,
    },
    {
      icon: 'music',
      title: 'Música5',
      date: '23/08/2022',
      finished: false,
    },
    {
      icon: 'music',
      title: 'Música6',
      date: '23/08/2022',
      finished: false,
    },
  ];

  return (
    <Container>
      <ScrollView showsVerticalScrollIndicator={false}>
        <Header />

        <List list={arr} />
      </ScrollView>
    </Container>
  );
}
