import React, { useState } from 'react';
import { Alert, ScrollView } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialCommunityIcons';
import { useTheme } from 'styled-components';
import Modal from 'react-native-modal';
import moment from 'moment';

import { Calendar, DayProps } from '../../components/Calendar';
import Header from '../../components/Header';
import { StoreProps, useTask } from '../../context/list';
import { Props } from '../../routes/app.routes';

import {
  Container,
  Info,
  GoBack,
  Row,
  Label,
  Value,
  Column,
  SaveButton,
  DataButton,
  DescriptionInput,
  SaveTitle,
} from './styles';
import Input from '../../components/Input';

export default function Task({ navigation, route }: Props) {
  const { list, setList } = useTask();
  const { task, type } = route.params;

  const [title, setTitle] = useState<string>(task?.title || '');
  const [scheduledDate, setScheduledDate] = useState<DayProps | null>(
    task?.scheduledDate || null,
  );
  const [store, setStore] = useState<StoreProps | null>(task?.store || null);
  const [description, setDescription] = useState<string | undefined>(
    task?.description || '',
  );

  const [value, setValue] = useState<string>(task?.value || '');

  const [modalVisible, setModalVisible] = useState(false);

  const theme = useTheme();

  const isNotTypeView = type !== 'view';

  const handleSave = () => {
    try {
      switch (type) {
        case 'add':
          const newElement = {
            id: task.id,
            icon: 'arrow',
            title,
            scheduledDate,
            conclusionDate: null,
            store,
            description,
            finished: false,
            value,
          };

          const newList = [...list];

          newList.push(newElement);

          setList(newList);

          break;

        case 'edit':
          const temp = list.map(item => {
            if (item.id === task.id) {
              item.title = title;
              item.scheduledDate = scheduledDate;
              item.description = description;
              item.value = value;

              return item;
            }

            return item;
          });

          setList(temp);
          break;
        default:
          break;
      }

      Alert.alert('Sucesso!', 'Informações foram salvas!');

      setTimeout(() => {
        navigation.goBack();
      }, 1000);
    } catch (error) {
      Alert.alert('Erro', 'Tente novamente mais tarde!');
    }
  };

  const onDateChange = (date: DayProps) => {
    const formattedDate = `${date.day}/${date.month}/${date.year}`;

    date.dateString = formattedDate;

    setScheduledDate(date);
    // setModalVisible(false);
  };

  return (
    <Container>
      <ScrollView showsVerticalScrollIndicator={false}>
        <Header title="223 dias" color={theme.colors.green} />

        <GoBack onPress={() => navigation.goBack()}>
          <Icon name="arrow-left" size={24} color={theme.colors.black} />
        </GoBack>

        <Info>
          <Row>
            <Icon
              name="note"
              size={24}
              color={theme.colors.gray}
              style={{ marginLeft: 10, marginRight: 10 }}
            />

            <Label>Título: </Label>

            <Input
              value={title}
              autoCorrect={false}
              autoCapitalize="none"
              onChangeText={setTitle}
              editable={isNotTypeView}
            />
          </Row>

          <Row>
            <Icon
              name="calendar-blank-outline"
              size={24}
              color={theme.colors.gray}
              style={{ marginLeft: 10, marginRight: 10 }}
            />

            <Label>Data prevista: </Label>

            <DataButton
              onPress={() => setModalVisible(true)}
              disabled={!isNotTypeView}
            >
              {scheduledDate?.dateString ? (
                <Value>{scheduledDate?.dateString}</Value>
              ) : (
                <Value>DD/MM/AAAA</Value>
              )}
            </DataButton>
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
              initialDate={
                task.scheduledDate?.dateString
                  ? task.scheduledDate.dateString.split('/').reverse().join('-')
                  : moment().format('L').split('/').reverse().join('-')
              }
              style={{ borderRadius: 10 }}
              onDayPress={date => onDateChange(date)}
              headerStyle={{
                borderBottomWidth: 0.5,
                paddingBottom: 10,
                marginBottom: 10,
              }}
            />
          </Modal>

          {task.conclusionDate ? (
            <Row>
              <Icon
                name="calendar-blank-outline"
                size={24}
                color={theme.colors.green}
                style={{ marginLeft: 10, marginRight: 10 }}
              />

              <Label color>Data conclusão:</Label>

              <DataButton disabled color>
                <Value color>{task.conclusionDate.dateString}</Value>
              </DataButton>
            </Row>
          ) : null}

          <Row>
            <Icon
              name="store-outline"
              size={24}
              color={theme.colors.gray}
              style={{ marginLeft: 10, marginRight: 10 }}
            />

            <Label>Loja: </Label>

            <DataButton disabled={!isNotTypeView}>
              <Value>{task.store?.name || 'Loja'}</Value>
            </DataButton>
          </Row>

          <Row>
            <Icon
              name="currency-usd"
              size={24}
              color={theme.colors.gray}
              style={{ marginLeft: 10, marginRight: 10 }}
            />

            <Label>Valor: </Label>

            <Input
              value={value}
              autoCorrect={false}
              autoCapitalize="none"
              onChangeText={setValue}
              editable={isNotTypeView}
            />
          </Row>

          <Column>
            <Row>
              <Icon
                name="text-box-outline"
                size={24}
                color={theme.colors.gray}
                style={{ marginLeft: 10, marginRight: 10 }}
              />

              <Label>Descrição: </Label>
            </Row>

            <DescriptionInput
              value={description}
              placeholder={description ? '' : 'Descrição'}
              autoCorrect={false}
              autoCapitalize={false}
              onChangeText={(text: string) => setDescription(text)}
              editable={isNotTypeView}
            />
          </Column>

          {type === 'view' ? null : (
            <SaveButton onPress={() => handleSave()}>
              <SaveTitle>Salvar</SaveTitle>
            </SaveButton>
          )}
        </Info>
      </ScrollView>
    </Container>
  );
}
