import React, { useState } from 'react';
import { Alert, ScrollView } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialCommunityIcons';
import { useTheme } from 'styled-components';
import Modal from 'react-native-modal';
import { Calendar, DayProps } from '../../components/Calendar';
import Header from '../../components/Header';
import { StoreProps, useTask } from '../../context/list';
import { Props } from '../../routes/app.routes';
import Input from '../../components/Input';

import {
  Container,
  Info,
  Button,
  Row,
  Label,
  Value,
  Column,
  SaveButton,
  DateButton,
  DescriptionLabel,
  DescriptionInput,
  StoreButton,
} from './styles';

export default function Task({ navigation, route }: Props) {
  const { list, setList } = useTask();
  const { task } = route.params;
  const [scheduledDate, setScheduledDate] = useState<DayProps | null>(
    task?.scheduledDate || null,
  );
  const [conclusionDate, setConclusionDate] = useState<DayProps | null>(
    task?.conclusionDate || null,
  );
  const [store, setStore] = useState<StoreProps | undefined>(task?.store);
  const [description, setDescription] = useState<string | undefined>(
    task?.description || '',
  );
  const [modalVisible, setModalVisible] = useState(false);

  const theme = useTheme();

  const handleSave = () => {
    try {
      const temp = list.map(item => {
        if (item.title === task.title) {
          item.description = description;

          item.scheduledDate = scheduledDate;

          return item;
        }
        return item;
      });

      setList(temp);

      Alert.alert('Sucesso!', 'Informações foram salvas!');
    } catch (error) {
      Alert.alert('Erro', 'Tente novamente mais tarde!');
    }
  };

  const onDateChange = (date: DayProps) => {
    const formattedDate = `${date.day}/${date.month}/${date.year}`;

    date.dateString = formattedDate;

    setScheduledDate(date);
    setModalVisible(false);
  };

  return (
    <Container>
      <ScrollView showsVerticalScrollIndicator={false}>
        <Header banner={task.title} color={theme.colors.red} />

        <Button onPress={() => navigation.goBack()}>
          <Icon name="arrow-left" size={24} color={theme.colors.black} />
        </Button>

        <Info>
          <Column>
            <Row>
              <Icon
                name="calendar-blank-outline"
                size={24}
                color={theme.colors.gray}
                style={{ marginLeft: 10, marginRight: 10 }}
              />

              <Label>Data prevista: </Label>

              <DateButton onPress={() => setModalVisible(true)}>
                <Value color={theme.colors.gray}>
                  {scheduledDate?.dateString ||
                    new Date().toLocaleDateString('pt-BR')}
                </Value>
              </DateButton>
            </Row>

            <Modal
              animationInTiming={1000}
              animationOutTiming={1000}
              backdropTransitionInTiming={1000}
              backdropTransitionOutTiming={0}
              isVisible={modalVisible}
              onBackdropPress={() => setModalVisible(false)}
            >
              <Calendar
                style={{ borderRadius: 10 }}
                markedDates={{ scheduledDate: { marked: true } }}
                onDayPress={date => onDateChange(date)}
                headerStyle={{
                  borderBottomWidth: 0.5,
                  paddingBottom: 10,
                  marginBottom: 10,
                }}
              />
            </Modal>
          </Column>

          {task.conclusionDate ? (
            <Column>
              <Row>
                <Icon
                  name="calendar-blank-outline"
                  size={24}
                  color={theme.colors.gray}
                  style={{ marginLeft: 10, marginRight: 10 }}
                />

                <Label>Data conclusão: </Label>
              </Row>

              <Value>{task.conclusionDate.toLocaleDateString('pt-BR')}</Value>
            </Column>
          ) : null}

          <Row>
            <Icon
              name="store-outline"
              size={24}
              color={theme.colors.gray}
              style={{ marginLeft: 10, marginRight: 10 }}
            />

            <Label>Loja: </Label>

            <StoreButton>
              <Value color={!!task.store?.name}>
                {task.store?.name || 'Loja'}
              </Value>
            </StoreButton>
          </Row>

          <Column style={{ paddingLeft: 24 }}>
            <DescriptionLabel>Descrição: </DescriptionLabel>

            {task.description ? (
              <DescriptionInput
                value={description}
                autoCorrect={false}
                autoCapitalize={false}
                onChangeText={(text: string) => setDescription(text)}
              />
            ) : (
              <DescriptionInput
                value={description}
                placeholder="Descrição"
                autoCorrect={false}
                autoCapitalize={false}
                onChangeText={(text: string) => setDescription(text)}
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
