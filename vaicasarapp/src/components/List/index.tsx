import { useNavigation } from '@react-navigation/native';
import React, { useEffect, useState } from 'react';
import { ActivityIndicator } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialCommunityIcons';
import { useTheme } from 'styled-components/native';
import { useTask } from '../../context/list';

import { Container, Item, Title, Date, Checkbox } from './styles';

export interface TaskProps {
  icon?: string;
  title: string;
  date: string;
  loja?: {
    name: string;
    category: string;
  };
  finished: boolean;
  description: string;
}

export default function List(): JSX.Element {
  const { list } = useTask();
  const [tasks, setTasks] = useState<TaskProps[]>([]);
  const navigation = useNavigation();
  const theme = useTheme();

  const changeValue = (item: TaskProps) => {
    const temp = list.map(i => {
      if (i.title === item.title) {
        i.finished = !item.finished;

        return i;
      }
      return i;
    });

    setTasks(temp);
  };

  const handleNavigation = (item: TaskProps) => {
    navigation.navigate('Task', { task: item });
  };

  const taskItem = (item: TaskProps) => {
    return (
      <Item key={item.title} onPress={() => handleNavigation(item)}>
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
