import { useFocusEffect, useNavigation } from '@react-navigation/native';
import React, { useCallback, useState } from 'react';
import { ActivityIndicator, Alert } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialCommunityIcons';
import { useTheme } from 'styled-components/native';
import moment from 'moment';
import { TaskProps, useTask } from '../../context/list';
import 'moment/locale/pt-br';

import {
  Container,
  Button,
  Item,
  Title,
  DateText,
  Checkbox,
  IconButton,
} from './styles';

export default function List(): JSX.Element {
  const { list, setList } = useTask();
  const [tasks, setTasks] = useState<TaskProps[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const navigation = useNavigation();
  const theme = useTheme();
  const empty: TaskProps = {
    id: null,
    title: '',
    scheduledDate: null,
    conclusionDate: null,
    finished: false,
    store: null,
    description: '',
    icon: 'arrow',
  };

  const changeValue = (item: TaskProps) => {
    const temp = list.map(i => {
      if (i.title === item.title) {
        i.finished = !item.finished;

        if (i.finished) {
          const currentDate = moment().format('L');

          const day = moment().get('date');
          const month = moment().get('month') + 1;
          const year = moment().get('year');
          const timestamp = moment().valueOf();

          i.conclusionDate = {
            dateString: currentDate,
            day,
            month,
            year,
            timestamp,
          };
        }

        return i;
      }
      return i;
    });

    setTasks(temp);
  };

  const handleAction = (item: TaskProps, { type }: { type: string }) => {
    switch (type) {
      case 'view':
        navigation.navigate('Task', { task: item, type: 'view' });
        break;
      case 'add':
        navigation.navigate('Task', { task: {}, type: 'add' });

        break;
      case 'edit':
        navigation.navigate('Task', { task: item, type: 'edit' });

        break;
      case 'delete':
        Alert.alert('Aviso', 'Tem certeza que deseja cancelar? ', [
          {
            text: 'Sim',
            onPress: () => {
              setLoading(true);

              const filteredList = tasks.filter(t => t.id !== item.id);

              setList(filteredList);

              setTimeout(() => {
                Alert.alert('Sucesso!', 'Item deletado com sucesso!');
                setLoading(false);
              }, 1000);
            },
          },
          {
            text: 'Não',
            style: 'cancel',
          },
        ]);

        break;
      default:
        break;
    }
  };

  const taskItem = (item: TaskProps) => {
    return (
      <Item
        key={item.title}
        onPress={() => handleAction(item, { type: 'view' })}
        check={item.finished}
      >
        <Checkbox check={item.finished} onPress={() => changeValue(item)}>
          {item.finished ? (
            <Icon name="check" size={24} color={theme.colors.green} />
          ) : null}
        </Checkbox>

        <Title>{item.title}</Title>

        <DateText>{item.scheduledDate?.dateString}</DateText>

        <IconButton onPress={() => handleAction(item, { type: 'edit' })}>
          <Icon
            name="pencil-outline"
            size={24}
            color={theme.colors.blue}
            style={{ marginLeft: 10, marginRight: 10 }}
          />
        </IconButton>

        <IconButton onPress={() => handleAction(item, { type: 'delete' })}>
          <Icon
            name="trash-can-outline"
            size={24}
            color={theme.colors.red}
            style={{ marginLeft: 10, marginRight: 10 }}
          />
        </IconButton>
      </Item>
    );
  };

  useFocusEffect(
    useCallback(() => {
      const handleData = () => {
        setTasks(list);
        setLoading(false);
      };

      setTimeout(() => {
        handleData();
      }, 1000);
    }, [list]),
  );

  return (
    <Container>
      <Button onPress={() => handleAction(empty, { type: 'add' })}>
        <Icon name="plus" size={24} color={theme.colors.white} />
      </Button>

      {loading ? (
        <ActivityIndicator size="large" />
      ) : (
        tasks.map(task => {
          return taskItem(task);
        })
      )}
    </Container>
  );
}
