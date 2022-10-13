import React, { useEffect } from 'react';
import { FlatList } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialCommunityIcons';
import { useTheme } from 'styled-components/native';

import { Item, Title, Date, Checkbox } from './styles';

export interface Task {
  icon?: string;
  title: string;
  date: string;
  finished: boolean;
}

export default function List({ list }: { list: Task[] }): JSX.Element {
  const theme = useTheme();

  const taskItem = ({ item }: { item: Task }) => {
    const v = item.finished;

    const changeValue = () => {
      if (v) {
        return <Icon name="check" size={24} color={theme.colors.gray} />;
      }
      return null;
    };

    return (
      <Item>
        <Icon
          name={item.icon ? item.icon : ''}
          size={24}
          color={theme.colors.gray}
          style={{ marginLeft: 10, marginRight: 10 }}
        />

        <Title>{item.title}</Title>

        <Date>{item.date}</Date>

        <Checkbox onPress={changeValue}>{changeValue()}</Checkbox>
      </Item>
    );
  };

  return (
    <FlatList
      data={list}
      renderItem={taskItem}
      keyExtractor={item => item.title}
      contentContainerStyle={{
        alignItems: 'center',
        paddingTop: 20,
      }}
    />
  );
}
