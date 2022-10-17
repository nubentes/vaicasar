import React from 'react';
import {
  Calendar as CustomCalendar,
  LocaleConfig,
  CalendarProps,
} from 'react-native-calendars';
import Icon from 'react-native-vector-icons/MaterialCommunityIcons';

import theme from '../../styles/theme';

import { ptBR } from './localeConfig';

LocaleConfig.locales['pt-br'] = ptBR;

LocaleConfig.defaultLocale = 'pt-br';

export interface DayProps {
  dateString: string;
  day: number;
  month: number;
  year: number;
  timestamp: number;
}

function Calendar({ onDayPress, markedDates }: CalendarProps) {
  return (
    <CustomCalendar
      renderArrow={direction => (
        <Icon
          size={24}
          color={theme.colors.black}
          name={direction === 'left' ? 'chevron-left' : 'chevron-right'}
        />
      )}
      headerStyle={{
        backgroundColor: theme.colors.white,
        borderBottomWidth: 0.5,
        borderBottomColor: theme.colors.red,
        paddingBottom: 10,
        marginBottom: 10,
      }}
      theme={{
        textDayFontSize: 15,
        textMonthFontSize: 20,
        monthTextColor: theme.colors.black,
        arrowStyle: {
          marginHorizontal: -15,
        },
        dayTextColor: theme.colors.black,
        selectedDayTextColor: theme.colors.red,
      }}
      style={{ borderRadius: 6 }}
      firstDay={1}
      onDayPress={onDayPress}
      markedDates={markedDates}
    />
  );
}

export { Calendar };
