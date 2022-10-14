import React, { useEffect, useState } from 'react';
import { ActivityIndicator } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialCommunityIcons';
import { useTheme } from 'styled-components/native';

import { Container, Item, Title, Date, Checkbox } from './styles';

export interface Task {
  icon?: string;
  title: string;
  date: string;
  finished: boolean;
}

export default function List({ list }: { list: Task[] }): JSX.Element {
  const [tasks, setTasks] = useState<Task[]>([]);

  const theme = useTheme();

  const changeValue = (item: Task) => {
    const temp = list.map(i => {
      if (i.title === item.title) {
        i.finished = !item.finished;

        return i;
      }
      return i;
    });

    setTasks(temp);
  };

  const taskItem = (item: Task) => {
    return (
      <Item key={item.title}>
        <Icon
          name={item.icon ? item.icon : ''}
          size={24}
          color={theme.colors.gray}
          style={{ marginLeft: 10, marginRight: 10 }}
        />

        <Title>{item.title}</Title>

        <Date>{item.date}</Date>

        <Checkbox onPress={() => changeValue(item)}>
          {item.finished ? (
            <Icon name="check" size={24} color={theme.colors.green} />
          ) : null}
        </Checkbox>
      </Item>
    );
  };

  useEffect(() => {
    if (tasks.length === 0) {
      setTimeout(() => {
        setTasks(list);
      }, 1000);
    }
  }, [list, tasks]);

  return (
    <Container>
      {tasks.length > 0 ? (
        tasks.map(task => {
          return taskItem(task);
        })
      ) : (
        <ActivityIndicator size="large" />
      )}
    </Container>
  );
}
