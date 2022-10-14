import React, { useState } from 'react';
import { Alert, ScrollView } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialCommunityIcons';
import { useTheme } from 'styled-components';
import Header from '../../components/Header';
import { useTask } from '../../context/list';
import { Props } from '../../routes/app.routes';

import {
  Container,
  Info,
  Button,
  Row,
  Label,
  Value,
  Column,
  Description,
  Input,
  SaveButton,
} from './styles';

export default function Task({ navigation, route }: Props) {
  const { list, setList } = useTask();
  const { task } = route.params;
  const [value, setValue] = useState<string>(task?.description);

  const theme = useTheme();

  const handleSave = () => {
    try {
      const temp = list.map(i => {
        if (i.title === task.title) {
          i.description = value;

          return i;
        }
        return i;
      });

      setList(temp);

      Alert.alert('Sucesso!', 'Informações foram salvas!');
    } catch (error) {
      Alert.alert('Erro', 'Tente novamente mais tarde!');
    }
  };

  return (
    <Container>
      <ScrollView showsVerticalScrollIndicator={false}>
        <Header banner={task.title} color={theme.colors.red} />

        <Button onPress={() => navigation.goBack()}>
          <Icon name="arrow-left" size={24} color={theme.colors.black} />
        </Button>

        <Info>
          <Row>
            <Icon
              name="calendar-blank-outline"
              size={24}
              color={theme.colors.gray}
              style={{ marginLeft: 10, marginRight: 10 }}
            />

            <Label>Data prevista: </Label>

            <Value>{task.date}</Value>
          </Row>

          <Row>
            <Icon
              name="calendar-blank-outline"
              size={24}
              color={theme.colors.gray}
              style={{ marginLeft: 10, marginRight: 10 }}
            />

            <Label>Data conclusão: </Label>

            <Value>{task.date}</Value>
          </Row>

          <Row>
            <Icon
              name="store-outline"
              size={24}
              color={theme.colors.gray}
              style={{ marginLeft: 10, marginRight: 10 }}
            />

            <Label>Loja: </Label>

            <Value>{task.loja?.name}</Value>
          </Row>

          <Column>
            <Description>Descrição: </Description>

            {task.description ? (
              <Input
                value={value}
                autoCorrect={false}
                autoCapitalize={false}
                // defaultValue={task.description}
                onChangeText={(text: string) => setValue(text)}
              />
            ) : (
              <Input
                value={value}
                placeholder="Descrição"
                autoCorrect={false}
                autoCapitalize={false}
                onChangeText={(text: string) => setValue(text)}
              />
            )}
          </Column>

          <SaveButton onPress={() => handleSave()}>
            <Label style={{ color: theme.colors.white }}>Salvar</Label>
          </SaveButton>
        </Info>
      </ScrollView>
    </Container>
  );
}
