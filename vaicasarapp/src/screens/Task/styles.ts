import styled from 'styled-components/native';

interface Props {
  color: boolean;
  weight: boolean;
}

export const Container = styled.View`
  flex: 1;

  background-color: ${({ theme }) => theme.colors.white};
`;

export const Info = styled.View`
  flex: 1;

  margin-top: 20px;
`;

export const GoBack = styled.TouchableOpacity`
  position: absolute;
  left: 10px;
  top: 30px;
`;

export const Row = styled.View`
  padding: 0px 24px;

  flex-direction: row;

  justify-content: space-between;
  align-items: center;

  margin: 10px;
`;

export const Column = styled.View`
  flex-direction: column;
`;

export const TitleInput = styled.TextInput`
  width: 130px;
  height: 40px;

  border-radius: 6px;

  border-width: 1px;
  border-style: solid;
  border-color: ${({ theme }) => theme.colors.black};

  color: ${({ theme }) => theme.colors.black};

  padding-left: 10px;

  font-size: 16px;
`;

export const DataButton = styled.TouchableOpacity<Props>`
  width: 130px;
  height: 40px;

  border-radius: 6px;
  border-color: ${({ theme, color }) =>
    color ? theme.colors.green : theme.colors.black};
  border-width: 1px;
  border-style: solid;

  flex-direction: row;

  align-items: center;
  justify-content: center;
`;

export const Label = styled.Text<Props>`
  font-size: 16px;
  font-weight: ${({ weight }) => (weight ? '500' : '400')};
  color: ${({ theme, color }) =>
    color ? theme.colors.green : theme.colors.black};
  flex-grow: 1;
`;

export const Value = styled.Text<Props>`
  font-size: 16px;
  font-weight: ${({ weight }) => (weight ? '500' : '400')};
  color: ${({ theme, color }) =>
    color ? theme.colors.green : theme.colors.black};
`;

export const DescriptionInput = styled.TextInput`
  width: 250px;
  height: 40px;

  border-radius: 6px;
  margin-top: 20px;
  margin-left: 58px;
  margin-bottom: 20px;

  border-width: 1px;
  border-style: solid;
  border-color: ${({ theme }) => theme.colors.black};

  color: ${({ theme }) => theme.colors.black};

  padding-left: 10px;
`;

export const SaveButton = styled.TouchableOpacity`
  width: 100px;
  height: 40px;

  background-color: ${({ theme }) => theme.colors.green};

  align-items: center;
  justify-content: center;

  border-radius: 6px;

  margin-top: 20px;
  margin-bottom: 20px;

  align-self: center;
`;

export const SaveTitle = styled.Text`
  font-size: 16px;
  font-weight: 400;
  color: ${({ theme }) => theme.colors.white};
`;
